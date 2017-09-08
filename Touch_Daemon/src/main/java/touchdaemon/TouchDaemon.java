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
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import net.Communicator;

/**
 *
 * @author Ivan
 */
public class TouchDaemon {
    
    public static final String SERVER_PATH = "c:/Server/";
    public static final String SERVER_PATH_LAN = SERVER_PATH + "lan/";
    public static final String SERVER_PATH_LAN4SRV = SERVER_PATH + "lan4srv/";
    public static final String IMAGES = SERVER_PATH + "images/cafe/";
    public static final String WEB_PATH = "c:/web/mtxwm/gm/hoc/par/web/";
    public static final String HOC_PATH = "c:/web/mtxwm/gm/hoc/par/";
    public static final String WEB_KEYFILE = "ASRPARAM.CTL";
    
    public static final Logger LOGGER = Logger.getAnonymousLogger().getParent();
    
    public TouchDaemon() {
        LOGGER.removeHandler(LOGGER.getHandlers()[0]);
        
        Formatter formatter = new Formatter() {
            public String format(LogRecord arg0) {
                StringBuilder b = new StringBuilder();
                b.append(new Date());
                b.append(" ");
                b.append(arg0.getSourceClassName());
                b.append(" ");
                b.append(arg0.getSourceMethodName());
                b.append(" ");
                b.append(arg0.getLevel());
                b.append(" ");
                b.append(arg0.getMessage());
                b.append(System.getProperty("line.separator"));
                return b.toString();
            }
        };
        
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        ch.setFormatter(formatter);
        LOGGER.addHandler(ch);
        
        try {
            FileHandler fh = new FileHandler("touchdaemon.log");
            fh.setFormatter(formatter);
            fh.setLevel(Level.ALL);
            LOGGER.addHandler(fh);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "logging problem!", e);
        }

        // load settings
        ConfigReader configReader = new ConfigReader();
        if (configReader.check()) {
            LOGGER.log(Level.FINEST, "config read success!");
        } else {
            LOGGER.log(Level.SEVERE, "config read fail!");
            System.exit(1);
        }
        
        LOGGER.log(Level.INFO, "starting daemon...");
        String response
                = configReader.readSharePath() + " "
                + configReader.readUsername() + " "
                + configReader.readPassword() + " "
                + configReader.readLoadTime();
        LOGGER.log(Level.INFO, "communicator starting...");
        Communicator communicator = new Communicator(configReader.readPort(), response);
        communicator.start();
        LOGGER.log(Level.INFO, "trigger starting...");
        DayTrigger dayTrigger = new DayTrigger(configReader.readPath(), configReader.readLoadTime(), configReader.readParSettings(),
                configReader.readRefSettings());
        dayTrigger.start();
        LOGGER.log(Level.INFO, "Daemon started!\n**************************\n");
    }
    
    public static void main(String[] args) {
        TouchDaemon daemon = new TouchDaemon();
    }
}
