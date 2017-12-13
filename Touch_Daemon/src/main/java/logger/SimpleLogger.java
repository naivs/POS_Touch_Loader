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
package logger;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 *
 * @author Ivan Naumov
 */
public class SimpleLogger implements CustomLogger {

    private Logger logger; 

    public SimpleLogger() {
        logger = Logger.getAnonymousLogger().getParent();

        logger.removeHandler(logger.getHandlers()[0]);
        
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
            logger.addHandler(fh);
        } catch (IOException e) {
            logger.log(Level.WARNING, "logging problem!", e);
        }
    }

    @Override
    public void log(int LEVEL, String message) {
        logger.log(getLevel(LEVEL), message);
    }

    @Override
    public void log(int LEVEL, String message, Throwable t) {
        logger.log(getLevel(LEVEL), message, t);
    }

    @Override
    public void setLevel(int LEVEL) {
        logger.setLevel(getLevel(LEVEL));
    }
    
    private Level getLevel(int level) {
        switch (level) {
            case DEBUG:
                return Level.ALL;
                
            case INFO:
                return Level.INFO;
                
            case WARN:
                return Level.WARNING;
                
            case CRIT:
                return Level.SEVERE;
                
            default:
                return Level.WARNING;
        }
    }
}
