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
package touchdaemon;

import io.ConfigReader;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.Server;

/**
 *
 * @author Ivan
 */
public class TouchDaemon {

    public static final String APPLICATION_NAME = "Touch Daemon";
    public static final String ICON_STR = "icon.png";

    public static final String SERVER_PATH = "c:/Server/";
    public static final String SERVER_PATH_LAN = SERVER_PATH + "lan/";
    public static final String SERVER_PATH_LAN4SRV = SERVER_PATH + "lan4srv/";
    public static final String IMAGES = SERVER_PATH + "images/cafe/";
    public static final String WEB_PATH = "c:/web/mtxwm/gm/hoc/par/web/";
    public static final String HOC_PATH = "c:/web/mtxwm/gm/hoc/par/";
    public static final String WEB_KEYFILE = "ASRPARAM.CTL";

    public static final Logger LOGGER = Logger.getAnonymousLogger().getParent();
    
    private final Server server;

    public TouchDaemon() {
        LOGGER.removeHandler(LOGGER.getHandlers()[0]);

        Formatter formatter = new Formatter() {
            @Override
            public String format(LogRecord arg0) {
                StringBuilder b = new StringBuilder();
                b.append(new Date());
                b.append(" ");
//                b.append(arg0.getSourceClassName());
//                b.append(" ");
//                b.append(arg0.getSourceMethodName());
//                b.append(" ");
                b.append(arg0.getLevel());
                b.append(" ");
                b.append(arg0.getMessage());
                b.append(System.getProperty("line.separator"));
                return b.toString();
            }
        };
        
        try {
            FileHandler fh = new FileHandler("touchdaemon.log");
            fh.setFormatter(formatter);
            fh.setLevel(Level.ALL);
            LOGGER.addHandler(fh);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "logging problem!", e);
        }

        LOGGER.log(Level.INFO, "starting daemon...");
                
        LOGGER.log(Level.INFO, "server starting...");
        server = new Server(8080);
        server.startServer();
        LOGGER.log(Level.INFO, "Daemon started!\n**************************\n");

        // SET TRAY ICON
        if (!SystemTray.isSupported()) {
            LOGGER.log(Level.WARNING, "System Tray is not supported on that OS!");
            return;
        }
        PopupMenu trayMenu = new PopupMenu();

        MenuItem itemStatus = new MenuItem("Status");
        itemStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, server.status(), "Status", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        trayMenu.add(itemStatus);

        MenuItem itemExit = new MenuItem("Exit");
        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stopServer();
                System.exit(0);
            }
        });
        trayMenu.add(itemExit);

        Image icon = Toolkit.getDefaultToolkit().getImage(ICON_STR);
        TrayIcon trayIcon = new TrayIcon(icon, APPLICATION_NAME, trayMenu);
        trayIcon.setImageAutoSize(true);

        SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            LOGGER.log(Level.WARNING, "Tray icon not shown...", e);
        }

        trayIcon.displayMessage(APPLICATION_NAME, "Application started!",
                TrayIcon.MessageType.INFO);
    }

    public static void main(String[] args) {
        try {
            System.in.close();
            System.out.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Unable to close system streams...", e);
        }

        TouchDaemon daemon = new TouchDaemon();
    }
}
