import jssc.SerialPortException;
import jssc.SerialPortList;
import keyboard.KeyConfigurator;
import serial.Connection;
import serial.ConnectionImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

public class GUI extends JFrame implements Observer {

    private JComboBox<String> comboBox1;
    private JButton setKeysButton;
    private JButton connectButton;
    private JPanel jPanel;
    private JToggleButton tButtonPad1;
    private JToggleButton tButtonPad2;

    private Connection connection;
    private java.util.List<String> ports;

    private final KeyConfigurator keyConfigurator;
    private static final int[] CODE = {
        1,
        2,
        4,
        8,
        16,
        32,
        64,
        128,
        256,
        512,
        1024,
        2048
    };
    private static Robot robot;

    GUI() throws HeadlessException {
        setContentPane(jPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sega controls");
        setResizable(false);
        setMinimumSize(new Dimension(877, 400));
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                ((Display) jPanel).log("Exit...");
                System.out.println("Exit...");
                if (connection != null) {
                    connection.close();
                }
            }
        });

        ports = new ArrayList<>();
        ports.add("");
        ports.addAll(
                Arrays.asList(SerialPortList.getPortNames("/dev/", Pattern.compile("^ttyUSB[0-9]+$")))
        );

        System.out.println("Available ports: ");
        ((Display) jPanel).log("Available ports: ");
        ports.forEach(port -> {
            System.out.println(port);
            ((Display) jPanel).log(port);
            comboBox1.addItem(port);
        });

        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.err.println("Can't create robot.\n" + e.getMessage());
        }

        keyConfigurator = new KeyConfigurator(this);

        tButtonPad1.addActionListener(e -> {
            if (tButtonPad1.isSelected()) {
                ((Display) jPanel).log("Gamepad 1 enabled");
            } else {
                ((Display) jPanel).log("Gamepad 1 disabled");
            }
        });

        tButtonPad2.addActionListener(e -> {
            if (tButtonPad2.isSelected()) {
                ((Display) jPanel).log("Gamepad 2 enabled");
            } else {
                ((Display) jPanel).log("Gamepad 2 disabled");
            }
        });

        connectButton.addActionListener(e -> {
            try {
                if (connection == null) {
                    ((Display) jPanel).log("No connection");
                    return;
                }

                if (!connection.isOpen()) {
                    connection.addObserver(this);
                    connection.open();
                    ((Display) jPanel).log("Connected");
                } else {
                    ((Display) jPanel).log("Connected already");
                }
                SwingUtilities.invokeLater(() -> {
                    try {
                        Thread.sleep(2000L);
                        connection.send(ConnectionImpl.RQ_START);
                    } catch (Exception ignored) {}
                });
            } catch (SerialPortException ex) {
                ex.printStackTrace();
                ((Display) jPanel).log("Unexpected error..");
            }
        });

        setKeysButton.addActionListener(e -> keyConfigurator.setVisible(true));
        comboBox1.addActionListener(e -> {
            String selectedPort = ports.get(comboBox1.getSelectedIndex());
            connection = selectedPort.isEmpty() ? null : new ConnectionImpl(selectedPort);
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        String className = o.getClass().getName();
        if (className.equals(ConnectionImpl.class.getName())) {

            if (arg.equals(Connection.RPL_STAT)) {
//            connection = (Connection) arg;
//            connection.addObserver(this);
                ((Display) jPanel).log("Gamepad connected");
            } else if (arg.equals(Connection.RPL_START)) {
                ((Display) jPanel).log("Handle started");
            } else {
                //System.out.print("Button data! Response: " + arg);

                String[] raw;
                int code_1, code_2;
                int i;

                try {
                    if (arg.toString().contains(" ")) {
                        raw = arg.toString().split(" ");

                        if (tButtonPad1.isSelected()) {
                            code_1 = raw[0].isEmpty() ? 0 : Integer.parseInt(raw[0]);
                        } else {
                            code_1 = 0;
                        }

                        if (tButtonPad2.isSelected()) {
                            code_2 = raw[1].isEmpty() ? 0 : Integer.parseInt(raw[1].trim());
                        } else {
                            code_2 = 0;
                        }

                        for (i = 0; i < CODE.length; i++) {
                            if ((code_1 & CODE[i]) == CODE[i]) {
                                robot.keyPress(keyConfigurator.getMap().getKey(i, 0));
                                System.out.println("Joy 1: press-" + keyConfigurator.getMap().getKey(i, 0));
//                                ((Display) jPanel).log("Joy 1: press-" + keyConfigurator.getMap().getKey(i, 0));
                                ((Display) jPanel).press(0, i);
                            } else if (((Display) jPanel).isPressed(0, i)) {
                                robot.keyRelease(keyConfigurator.getMap().getKey(i, 0));
                                System.out.println("Joy 1: release-" + keyConfigurator.getMap().getKey(i, 0));
//                                ((Display) jPanel).log("Joy 1: press-" + keyConfigurator.getMap().getKey(i, 0));
                                ((Display) jPanel).release(0, i);
                            }

                            if ((code_2 & CODE[i]) == CODE[i]) {
                                robot.keyPress(keyConfigurator.getMap().getKey(i, 1));
                                System.out.println("Joy 2: press-" + keyConfigurator.getMap().getKey(i, 1));
//                                ((Display) jPanel).log("Joy 2: press-" + keyConfigurator.getMap().getKey(i, 1));
                                ((Display) jPanel).press(1, i);
                            } else if (((Display) jPanel).isPressed(1, i)) {
                                robot.keyRelease(keyConfigurator.getMap().getKey(i, 1));
                                System.out.println("Joy 2: release-" + keyConfigurator.getMap().getKey(i, 1));
//                                ((Display) jPanel).log("Joy 2: press-" + keyConfigurator.getMap().getKey(i, 1));
                                ((Display) jPanel).release(1, i);
                            }
                        }
                        jPanel.repaint();
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void createUIComponents() {
        jPanel = new Display();
    }
}
