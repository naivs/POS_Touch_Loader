/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serial;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author Ivan Naumov
 */
public class PortFinder extends Observable implements Runnable, Observer {

    private boolean isRunning;
    private String[] systemPorts;
    private ConnectionImpl connection;

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void run() {
        isRunning = true;
        while (isRunning) {
            System.out.println("Waiting for device...");
            systemPorts = SerialPortList.getPortNames("/dev/");
            System.out.println("Available ports: " + Arrays.toString(systemPorts));
            for (String portName : systemPorts) {
                if (connection == null) {
                    connection = new ConnectionImpl(portName);
                    connection.addObserver(this);
                }

                if (!connection.isOpen()) {
                    try {
                        connection.open();
                        connection.send("222");
                    } catch (SerialPortException ex) {
                        //failed to open
                        System.err.println("Failed to open port " + portName
                                + "\n" + ex.getMessage());
                    }
                } else {
                    System.out.println(String.format("Device connected on %s port...", portName));
                    notifyObservers(connection);
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                //interrupted
            }
        }
    }

    public void stop() {
        isRunning = false;
        deleteObservers();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o.getClass().getName().equals(ConnectionImpl.class.getName())) {
            isRunning = false;
        }
    }
}
