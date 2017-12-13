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
import java.net.ServerSocket;
import javax.swing.JOptionPane;
import logger.CustomLogger;
import net.Server;
import services.LoggerService;

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
    
    private final Server server;

    public TouchDaemon() {
        LoggerService.getLogger().log(CustomLogger.INFO, "server starting...");
        server = new Server(8080);
        server.startServer();
        LoggerService.getLogger().log(CustomLogger.INFO, "Daemon is running!\n**************************\n");

        // SET TRAY ICON
        if (!SystemTray.isSupported()) {
            LoggerService.getLogger().log(CustomLogger.WARN, "System Tray is not supported on that OS!");
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
            LoggerService.getLogger().log(CustomLogger.WARN, "Tray icon not shown...", e);
        }

        trayIcon.displayMessage(APPLICATION_NAME, "Application started!",
                TrayIcon.MessageType.INFO);
    }

    public static void main(String[] args) {
        // protection from double running
        try {
            ServerSocket s = new ServerSocket(61321);
        } catch (IOException ex) {
            System.err.println(String.format("Was tryed to start another copy! Rejected...\n%s", ex));
            System.exit(1);
        }
        
        try {
            System.in.close();
            System.out.close();
        } catch (IOException e) {
            LoggerService.getLogger().log(CustomLogger.CRIT, "Unable to close system streams...", e);
        }
        
        TouchDaemon daemon = new TouchDaemon();
    }
}
