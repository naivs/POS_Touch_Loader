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
package io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import logger.CustomLogger;
import services.LoggerService;

/**
 *
 * @author Ivan
 */
public class ConfigReader {

    private String sharePath = "", username = "", password = "", path = "", loadTime = "";
    private int port;
    private boolean parSettings, refSettings;

    public ConfigReader() {
        Properties properties = new Properties();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("touchDaemon.conf")));
            properties.load(reader);
            sharePath = properties.getProperty("share_path");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            path = properties.getProperty("path");
            loadTime = properties.getProperty("load_time");
            parSettings = properties.getProperty("par_backup").equals("true");
            refSettings = properties.getProperty("ref_backup").equals("true");
            port = Integer.parseInt(properties.getProperty("port"));
        } catch (FileNotFoundException e) {
            LoggerService.getLogger().log(CustomLogger.CRIT, "File touchDaemon.conf is not found!", e);
        } catch (IOException e) {
            LoggerService.getLogger().log(CustomLogger.CRIT, "Other IO Exception.", e);
        } finally {
            try {
                if(reader != null) reader.close();
            } catch (IOException e) {
                LoggerService.getLogger().log(CustomLogger.CRIT, "Can't close the stream!", e);
            }
        }
    }

    public boolean check() {
        return !sharePath.trim().isEmpty() && !username.trim().isEmpty() && !password.trim().isEmpty()
                && loadTime != null;
    }

    public String readSharePath() {
        return sharePath;
    }
    
    public String readUsername() {
        return username;
    }

    public String readPassword() {
        return password;
    }

    public String readPath() {
        return path;
    }

    public String readLoadTime() {
        return loadTime;
    }

    public boolean readParSettings() {
        return parSettings;
    }

    public boolean readRefSettings() {
        return refSettings;
    }
    
    public int readPort() {
        return port;
    }
}
