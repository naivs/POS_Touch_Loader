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

import touchdaemon.TouchDaemon;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

/**
 *
 * @author Ivan
 */
public class ConfigReader {

    private String username = "", password = "", path = "", loadTime = "";
    private int port;
    private boolean parSettings, refSettings;

    public ConfigReader() {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("touchDaemon.conf")));
            String in;

            while ((in = reader.readLine()) != null) {
                if (!in.startsWith("#") && !in.isEmpty()) {
                    if (in.split("=").length > 1) {
                        switch (in.split("=")[0].trim()) {
                            case "username":
                                username = in.split("=")[1].trim();
                                break;
                            case "password":
                                password = in.split("=")[1].trim();
                                break;
                            case "path":
                                path = in.split("=")[1].trim();
                                break;
                            case "load_time":
                                loadTime = in.split("=")[1].trim();
                                break;
                            case "par_backup":
                                parSettings = Boolean.parseBoolean(in.split("=")[1].trim());
                                break;
                            case "ref_backup":
                                refSettings = Boolean.parseBoolean(in.split("=")[1].trim());
                                break;
                            case "port":
                                port = Integer.parseInt(in.split("=")[1].trim());
                                break;
                            default:
                                break;
                        }
                    } else {
                        throw new IOException("Wrong configs!");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            TouchDaemon.LOGGER.log(Level.SEVERE, "File touchDaemon.conf is not found!", e);
        } catch (IOException e) {
            TouchDaemon.LOGGER.log(Level.SEVERE, "Other IO Exception.", e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                TouchDaemon.LOGGER.log(Level.SEVERE, "Can't close the stream!", e);
            }
        }
    }

    public boolean check() {
        return !username.trim().isEmpty() && !password.trim().isEmpty()
                && loadTime != null;
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
