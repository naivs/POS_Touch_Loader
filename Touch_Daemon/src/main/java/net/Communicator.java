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
package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import touchdaemon.TouchDaemon;

/**
 *
 * @author ivan
 */
public class Communicator {
    
    private ServerSocket socketIn;
    private final String response;
    private Connection connection;
    
    public Communicator(int port, String response) {
        this.response = response;
        
        try {
            socketIn = new ServerSocket(port);
        } catch (IOException ex) {
            TouchDaemon.LOGGER.log(Level.SEVERE, "Unable to server socket up!", ex);
        }
        
        while (true) {
            try {
                if (connection != null && connection.isAlive()) {
                    Socket client1 = socketIn.accept();
                    Connection connection1 = new Connection(client1, Connection.DENY_MODE);
                    connection1.start();
                    TouchDaemon.LOGGER.log(Level.INFO, "Client {0} tried to connect...", connection1.getIP());
                } else {
                    Socket client = socketIn.accept();
                    connection = new Connection(client, Connection.ACCEPT_MODE);
                    connection.start();
                    TouchDaemon.LOGGER.log(Level.INFO, "Client {0} connected...", connection.getIP());
                }
            } catch (IOException ex) {
                TouchDaemon.LOGGER.log(Level.WARNING, "Failure connection with client: " + connection.getIP(), ex);
            }
        }
    }
    
    
    // === client connection thread ===
    private class Connection extends Thread {
        private final Socket socket;
        private int mode;

        public static final int ACCEPT_MODE = 1;
        public static final int DENY_MODE = -1;
        
        public Connection(Socket socket, int MODE) {
            this.socket = socket;
            this.mode = MODE;
        }
        
        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                if (mode == ACCEPT_MODE) {
                    out.println("[helo]");

                    String str;
                    while (true) {
                        str = in.readLine();
                        if (str.equals("[bye]")) {
                            break;
                        } else if(str.equals("[get]")) {
                            // -> params sending
                            out.println(response);
                        }
                    }
                } else {
                    out.println("[bye]");
                    close();
                }
            } catch (IOException e) {
                close();
            } finally {
                close();
            }
        }
        
        private void close() {   
            interrupt();
        }
        
        public String getIP() {
            return socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
        }
    }
}
