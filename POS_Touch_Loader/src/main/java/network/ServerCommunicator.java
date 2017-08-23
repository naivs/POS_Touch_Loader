/*
 * Copyright (C) 2017 Ivan Naumov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package network;

import gui.Emulator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 *
 * @author Ivan
 */
public class ServerCommunicator extends Thread {
    //private boolean stoped;
    private SMBAuthentication smbAuth;
    private String loadTime;
    
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    @Override
    public void run() {
        try {
            socket = new Socket(Emulator.SERVER_IP, Emulator.PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // testing connection
            String buf = in.readLine();
            if (buf.equals("[helo]")) {
                System.out.println(Emulator.SERVER_IP + ": connection established...");
            } else {
                System.out.println(Emulator.SERVER_IP + ": host unreachable.");
            }
            
            // sending query
            System.out.println("getting data...");
            out.println("[get]");
            String[] response = in.readLine().split(" "); // [path] [username] [password] [loadTime]
            System.out.println(Arrays.toString(response));
            smbAuth = new SMBAuthentication(response[0], response[1], response[2]);
            loadTime = response[3];
            out.println("[bye]");
        } catch (UnknownHostException ex) {
            System.err.println("Unknown host " + Emulator.SERVER_IP);
        } catch (IOException ex) {
            System.err.println("I/O Socket error.");
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Unable to close socket.");
            }
        }
    }
    
    public SMBAuthentication getSmbAuth() {
        return smbAuth;
    }
    
    public String getLoadTime() {
        return loadTime;
    }
}
