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
import java.net.UnknownHostException;
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

    private final String ip;
    private final SMBAuthentication smbAuth;
    private static final String SV = "smb://";
    private SmbFile share;

    public String testConnection() {
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(smbAuth.getUrl(), smbAuth.getUsername(), smbAuth.getPassword());
        String message = "[exists|read|write]: ";

        try {
            share = new SmbFile(SV + ip + smbAuth.getUrl() + "/", auth);
            message += share.exists() ? "ok" : "false";
            message += share.canRead() ? "|ok" : "|false";
            message += share.canWrite() ? "|ok" : "|false";
        } catch (MalformedURLException | SmbException ex) {
            System.err.println(ex.getMessage());
        }

        return message;
    }

    public SMBClient(String ip, SMBAuthentication smbAuth) {
        this.ip = ip;
        this.smbAuth = smbAuth;
    }

    public void createFolder(String name) {
        try {
            SmbFile dir = new SmbFile(share, name);
            dir.mkdir();
        } catch (UnknownHostException ex) {
            System.err.println("UnknownHostException. File: " + name + ". " + ex.getMessage());
        } catch (SmbException e) {
            System.err.println("Can't create directory " + name + ". " + e.getMessage());
        } catch (MalformedURLException e) {

        }
    }
    
    public void putFile(File src, String dest) throws MalformedURLException, IOException {
        SmbFile smbFile = new SmbFile(share, dest);
        //smbFile.createNewFile();
        Files.copy(src.toPath(), smbFile.getOutputStream());
    }

    public void putImages(String smbPath, File image) throws IOException {
//        try {
//            System.out.println(smbPath);
//
//            SmbFile dir = new SmbFile(this.address + smbPath + "/" + image.getName().substring(1) + "/");
//            dir.createNewFile();
//
//            Path source = Paths.get(image.getPath());
//
//            OutputStream out = dir.getOutputStream();
//            Files.copy(source, out);
//
//        } catch (SmbException e) {
//            System.err.println("Can't create file " + image.getPath() + ". " + e.getMessage());
//        } catch (MalformedURLException e) {
//
//        }
    }

//    public void putFile(String smbPath, File file) throws IOException {
//        try {
//            SmbFile dir = new SmbFile(this.address + smbPath + "/");
//            dir.createNewFile();
//            
//            Path source = Paths.get(file.getPath());
//
//            OutputStream out = dir.getOutputStream();
//            Files.copy(source, out);
//            
//        } catch (SmbException e) {
//            System.err.println("Can't create file " + smbPath + ". " + e.getMessage());
//        } catch (MalformedURLException e) {
//
//        }
//    }

    public void clearShare() {
        try {
            SmbFile[] files = share.listFiles();
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }
        } catch (SmbException e) {
            System.err.println("Can't clear. " + e.getMessage());
        }
    }
}
