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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;

/**
 *
 * @author Ivan
 */
public class Connection extends Observable implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private boolean isConnected;

    /**
     * 0 - get parameters for samba connection 1 - init upload data on POSes 2 -
     * test connection 3 - get time of upload data on POSes
     */
    public static final int SMB_PARAM_QUE = 0;
    public static final int UPLOAD_QUE = 1;
    public static final int TEST_QUE = 2;
    public static final int FIRE_TIME_QUE = 3;

    public Connection() {
        isConnected = false;
    }

    public void connect(String host, int port) throws IOException {
        if (!isConnected) {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            isConnected = true;

            Thread t = new Thread(this);
            t.start();
        }
    }

    public void request(int TYPE) {
        if (isConnected) {
            switch (TYPE) {
                case SMB_PARAM_QUE:
                    out.println(SMB_PARAM_QUE);
                    break;

                case UPLOAD_QUE:
                    out.println(UPLOAD_QUE);
                    break;

                case TEST_QUE:
                    out.println(TEST_QUE);
                    break;
                    
                case FIRE_TIME_QUE:
                    out.println(FIRE_TIME_QUE);
                    break;
            }
        }
    }

    @Override
    public void run() {
        String response = "";
        try {
            while (isConnected && (response = in.readLine()) != null) {
                this.setChanged();
                this.notifyObservers(response);
            }
        } catch (IOException ex) {
            System.err.println("Can't get response from server. " + ex.getMessage());
        } finally {
            isConnected = false;
        }
    }

    public void disconnect() {
        if(socket != null && isConnected) {
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println("Unable to close Socket. " + ex.getMessage());
            } finally {
                isConnected = false;
            }
        }
    }
    
    public boolean isConnected() {
        return isConnected;
    }
}
