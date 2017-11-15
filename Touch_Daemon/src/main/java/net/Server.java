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

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

/**
 *
 * @author ivan
 */
public class Server implements Observer {

    private final int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ServerThread serverThread;

    private final List<ClientThread> clients;

    private boolean isRunning;
    
    private final String wellcomeMsg;

    public Server(int port, String wellcomeMsg) {
        this.port = port;
        isRunning = false;
        clients = new ArrayList();
        this.wellcomeMsg = wellcomeMsg;
    }

    public void startServer() {
        if (!isRunning) {
            serverThread = new ServerThread();
            serverThread.start();
            isRunning = true;
            touchdaemon.TouchDaemon.LOGGER.log(Level.INFO,
                            String.format("Server started on %d port...", port));
        }
    }

    public void stopServer() {
        if (isRunning) {
            serverThread.stopServerThread();
            for (ClientThread client : clients) {
                client.stop();
            }
            isRunning = false;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        clients.remove((ClientThread) o);
    }

    public boolean status() {
        return isRunning;
    }
    
    private class ServerThread extends Thread {

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(port);

                while (isRunning) {
                    clientSocket = serverSocket.accept();
                    touchdaemon.TouchDaemon.LOGGER.log(Level.INFO,
                            "Client connected...");

                    ClientThread clientThread = new ClientThread(clientSocket, wellcomeMsg, clients.size());
                    clientThread.addObserver(Server.this);
                    clients.add(clientThread);
                    new Thread(clientThread).start();
                }
            } catch (IOException ex) {
                touchdaemon.TouchDaemon.LOGGER.log(Level.WARNING,
                        "Error occured on creating ServerSocket or accepting client connection.",
                        ex);
            }
        }

        public void stopServerThread() {
            try {
                serverSocket.close();
            } catch (IOException ex) {
                touchdaemon.TouchDaemon.LOGGER.log(Level.WARNING,
                        "Unable to close serverSocket.", ex);
            }
        }
    }
}
