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

import io.ConfigReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import logger.CustomLogger;
import services.LoggerService;
import touchdaemon.DayTrigger;

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

    private final DayTrigger trigger;

    public Server(int port) {
        this.port = port;
        isRunning = false;
        clients = new ArrayList();

        // load settings
        ConfigReader configReader = new ConfigReader();
        if (configReader.check()) {
            LoggerService.getLogger().log(CustomLogger.INFO, "config read success!");
        } else {
            LoggerService.getLogger().log(CustomLogger.CRIT, "config read fail!");
            System.exit(1);
        }

        wellcomeMsg = configReader.readSharePath() + " "
                + configReader.readUsername() + " "
                + configReader.readPassword() + " "
                + configReader.readLoadTime();
        trigger = new DayTrigger(configReader.readPath(), configReader.readLoadTime(), configReader.readParSettings(),
                configReader.readRefSettings());
    }

    public void startServer() {
        if (!isRunning) {
            serverThread = new ServerThread();
            serverThread.start();
            isRunning = true;
            LoggerService.getLogger().log(CustomLogger.INFO,
                    String.format("Server started on %d port!", port));
            trigger.addObserver(this);
            trigger.start();
        }
    }

    public void stopServer() {
        if (isRunning) {
            serverThread.stopServerThread();
            for (ClientThread client : clients) {
                client.stop();
            }
            isRunning = false;
            LoggerService.getLogger().log(CustomLogger.INFO, "Server stoped!");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        LoggerService.getLogger().log(CustomLogger.INFO, o.getClass().getName());
        if (o.getClass().getName().equals("net.ClientThread")) {
            /*
            here can be processed following args:
            0 - remove disconnected client
            1 - init upload on POSes
            */
            switch (Integer.parseInt(String.valueOf(arg))) {
                case 0:
                    clients.remove((ClientThread) o);
                    break;

                case 1:
                    trigger.upload(true);
                    break;
            }
        } else if (o.getClass().getName().equals("touchdaemon.DayTrigger")) {
            /*
            here can be processed following args:
            0 - signal successfull upload on POSes
            1 - signal fail upload
            */
            switch(Integer.parseInt(String.valueOf(arg))) {
                case 0:
                    if(clients.size() > 0) {
                        clients.get(0).sendMessage("0");
                    }
                    break;
                    
                case 1:
                    if(clients.size() > 0) {
                        clients.get(0).sendMessage("1");
                    }
                    break;
            }
        }
    }

    public String status() {
        return String.format("Сервер онлайн: %b\n%s", isRunning, trigger.getInfoStatus());
    }

    private class ServerThread extends Thread {

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(port);

                while (isRunning) {
                    clientSocket = serverSocket.accept();
                    LoggerService.getLogger().log(CustomLogger.INFO,
                            String.format("Client %s:%d connected!", clientSocket.getInetAddress().getHostAddress(), clientSocket.getPort()));

                    ClientThread clientThread = new ClientThread(clientSocket, wellcomeMsg, clients.size());
                    clientThread.addObserver(Server.this);
                    clients.add(clientThread);
                    new Thread(clientThread).start();
                }
            } catch (IOException ex) {
                LoggerService.getLogger().log(CustomLogger.WARN,
                        "Error occured on creating ServerSocket or accepting client connection.",
                        ex);
            }
        }

        public void stopServerThread() {
            try {
                serverSocket.close();
            } catch (IOException ex) {
                LoggerService.getLogger().log(CustomLogger.WARN, "Unable to close serverSocket.", ex);
            }
        }
    }
}
