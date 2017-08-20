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
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import net.Communicator;

/**
 *
 * @author Ivan
 */
public class TouchDaemon {

    public static final String SERVER_PATH = "c$/Server/";
    public static final String SERVER_PATH_LAN =  SERVER_PATH + "lan/";
    public static final String SERVER_PATH_LAN4SRV =  SERVER_PATH + "lan4srv/";
    public static final String IMAGES = SERVER_PATH + "cafe/";
    public static final String WEB_PATH = "c$/web/mtxwm/gm/hoc/par/web/";
    public static final String HOC_PATH = "c$/web/mtxwm/gm/hoc/par/";
    public static final String WEB_KEYFILE = "ASRPARAM.CTL";

    public static final Logger LOGGER = Logger.getLogger("touchdaemon");

    public TouchDaemon() {
        ConsoleHandler ch = new ConsoleHandler();
        LOGGER.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("touchdaemon.log");
            fh.setLevel(Level.ALL);
            fh.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fh);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "logging problem!", e);
        }
        LOGGER.setLevel(Level.ALL);

        // load settings
        ConfigReader configReader = new ConfigReader();
        if (configReader.check()) {
            LOGGER.log(Level.FINEST, "config read success!");
        } else {
            LOGGER.log(Level.SEVERE, "config read fail!");
            System.exit(1);
        }

        System.out.println("Daemon starting...");
        // -> starting communicator
        // -> starting trigger
        String response = "[" + 
                configReader.readIp() +
                configReader.readPath() +
                configReader.readUsername() +
                configReader.readPassword() +
                configReader.readLoadTime()
                + "]";
        Communicator communicator = new Communicator(configReader.readPort(), response);
        communicator.start();
        

        DayTrigger trigger = new DayTrigger(configReader.readPath(), configReader.readLoadTime(), configReader.readParSettings(),
        configReader.readRefSettings());
        
//        try {
//            smbClient.checkConnection();
//            LOGGER.log(Level.FINEST, "Connetcion established.");
//        } catch (MalformedURLException e) {
//            LOGGER.log(Level.SEVERE, "Bad Network name.", e);
//        } catch (SmbException e) {
//            LOGGER.log(Level.SEVERE, "Unable connect to share.", e);
//        }
    }

    public static void main(String[] args) {
        TouchDaemon daemon = new TouchDaemon();
    }
}
