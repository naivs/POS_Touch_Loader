/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serial;

import jssc.SerialPortException;

/**
 *
 * @author Ivan Naumov
 */
public class ConnectionFake extends Connection {

    private String data_press = "000111 001100";
    private String data_release = "000000 000000";
    private String code = "";

    private final String portName;
    private boolean isOpen;

    private final Thread com;

    public ConnectionFake(String portName) {
        this.portName = portName;

        com = new Thread(() -> {
            while (isOpen) {
                setChanged();

                if (code != null && !code.equals("")) {
                    switch (code) {
                        case RQ_START:
                            notifyObservers(RPL_START);
                            break;
                        case RQ_STAT:
                            notifyObservers(RPL_STAT);
                            break;
                        default:
                            break;
                    }
                    code = "";
                } else {
                    notifyObservers(data_press);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {

                    }
                    notifyObservers(data_release);

                }
            }
        });
    }

    @Override
    public void open() throws SerialPortException {
        isOpen = true;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public void send(String code) {
        this.code = code;

        if (code.equals(RQ_START)) {
            com.start();
        }
    }

    @Override
    public void close() {
        isOpen = false;
    }
}
