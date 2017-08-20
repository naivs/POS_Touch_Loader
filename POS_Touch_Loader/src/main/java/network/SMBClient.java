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

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbAuthException;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

/**
 *
 * @author Ivan
 */
public class SMBClient {

    private String ip;
    private SMBAuthentication smbAuth;

    public String testConnection() {

        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(smbAuth.getURL(), smbAuth.getUsername(), smbAuth.getPassword());
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

    public SMBClient(String ip, SMBAuthentication smbAuth) {

        this.ip = ip;
        this.smbAuth = smbAuth;
    }

    public String listFiles() throws MalformedURLException, SmbAuthException, SmbException {

        String out = "";

        SmbFile root = new SmbFile(this.address, "", this.getNtlmPasswordAuthentication());
        String[] files = root.list();

        for (int i = 0; i < files.length; i++) {
            out += files[i] + "\n";
        }

        return out;
    }

    public void createFolder(String name) {

        try {
            SmbFile dir = new SmbFile(this.address + name + "/");
            dir.mkdir();
        } catch (SmbException e) {
            System.err.println("Can't create directory " + name + ". " + e.getMessage());
        } catch (MalformedURLException e) {

        }
    }

    public void putImage(String smbPath, File image) throws IOException {

        try {
            System.out.println(smbPath);

            SmbFile dir = new SmbFile(this.address + smbPath + "/" + image.getName().substring(1) + "/");
            dir.createNewFile();

            Path source = Paths.get(image.getPath());

            OutputStream out = dir.getOutputStream();
            Files.copy(source, out);

        } catch (SmbException e) {
            System.err.println("Can't create file " + image.getPath() + ". " + e.getMessage());
        } catch (MalformedURLException e) {

        }
    }

    public void putFile(String smbPath, File file) throws IOException {

        try {
            SmbFile dir = new SmbFile(this.address + smbPath + "/");
            dir.createNewFile();
            
            Path source = Paths.get(file.getPath());

            OutputStream out = dir.getOutputStream();
            Files.copy(source, out);
            
        } catch (SmbException e) {
            System.err.println("Can't create file " + smbPath + ". " + e.getMessage());
        } catch (MalformedURLException e) {

        }
    }

    public void clearFolder() {

        try {
            SmbFile[] files = new SmbFile(address).listFiles();

            System.out.println(address);

            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }
        } catch (SmbException e) {
            System.err.println("Can't clear. " + e.getMessage());
        } catch (MalformedURLException e) {

        }
    }
}
