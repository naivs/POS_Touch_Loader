/*
 * Copyright (C) 2017 Ivan
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
package network;

import java.net.MalformedURLException;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

/**
 *
 * @author Ivan
 */
public class Authorization {

    public static final String USERNAME = "administrator";
    public static final String DOMAIN = "/c$/Server/pos/";

    public static String check_connection(String url, String username, String password) {

        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(url, username, password);
        String message = "";

        try {
            SmbFile file = new SmbFile("smb://" + url, auth);

            message += file.exists() ? "ok" : "false";
            message += file.canRead() ? "|ok" : "|false";
            message += file.canWrite() ? "|ok" : "|false";

        } catch (MalformedURLException | SmbException ex) {
            System.err.println(ex.getMessage());
        }

        return message;
    }
}
