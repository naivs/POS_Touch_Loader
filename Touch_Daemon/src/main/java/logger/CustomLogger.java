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

/**
 *
 * @author Ivan Naumov
 */
public interface CustomLogger {
    
    static int DEBUG = 0;
    static int INFO = 1;
    static int WARN = 2;
    static int CRIT = 3;
    
    void log(int LEVEL, String message);

    void log(int LEVEL, String message, Throwable t);

    void setLevel(int LEVEL);
}
