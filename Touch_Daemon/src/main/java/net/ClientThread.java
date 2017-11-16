/*
 * Copyright (C) 2017 ivan
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
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import static touchdaemon.TouchDaemon.LOGGER;

/**
 *
 * @author ivan
 */
class ClientThread extends Observable implements Runnable {

    private int clientsCount;

    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private boolean isRunning;

    private String wellcomeMsg;

    public ClientThread(Socket socket, String wellcomeMsg, int clientsCount) {
        this.socket = socket;
        this.clientsCount = clientsCount;
        isRunning = false;
        this.wellcomeMsg = wellcomeMsg;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, "Unable to get streams from socket.", ex);
        }
    }

    @Override
    public void run() {
        String msg = ""; // will hold message from client

        if (clientsCount > 0) {
            out.println("Server is busy!");
            isRunning = false;
        } else {
            out.println(wellcomeMsg);
            isRunning = true;
            try {
                while ((msg = in.readLine()) != null && isRunning) {
                    switch (Integer.parseInt(msg)) {
                        case 0: // <- get samba parameters
                            out.println(msg);
                            break;

                        case 1: // <- init upload on POSes
                            setChanged();
                            notifyObservers("text");
                            out.println();
                            break;
                            
                        case 2: // <- test
                            out.println(wellcomeMsg);
                            break;
                    }
                    LOGGER.log(Level.INFO, String.format("Message from client: %s", msg));
                }
                isRunning = false;
            } catch (IOException ex) {
                LOGGER.log(Level.WARNING, "IOException in client loop.", ex);
                isRunning = false;
            }
        }

        // closing socket
        try {
            socket.close();
            setChanged();
            notifyObservers(this);
            LOGGER.log(Level.INFO,
                    "Client disconnected...");
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, "Unable to close ClientSocket.", ex);
        }
    }

    public void stop() {
        try {
            socket.close();
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, "Unable to close ClientSocket.", ex);
        }
    }
}
