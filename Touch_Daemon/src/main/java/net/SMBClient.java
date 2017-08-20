package net;

import touchdaemon.TouchDaemon;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import jcifs.smb.NtlmAuthenticator;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

/**
 *
 * @author Ivan Naumov
 */
public class SMBClient extends NtlmAuthenticator {

    private final String address;
    private final String username;
    private final String password;

    @Override
    protected NtlmPasswordAuthentication getNtlmPasswordAuthentication() {

        //System.out.println(getRequestingException().getMessage() + " for " + getRequestingURL());
        if (password.length() == 0) {
            return null;
        }
        return new NtlmPasswordAuthentication(null, username, password);
    }

    public SMBClient(String address, String username, String password) {

        this.address = "smb://" + address + "/";
        this.username = username;
        this.password = password;

        NtlmAuthenticator.setDefault(this);
    }

    public boolean checkConnection() throws MalformedURLException, SmbException {

        SmbFile root = new SmbFile(this.address, this.getNtlmPasswordAuthentication());
        //root.setReadWrite();
        return root.canWrite();
    }

    public void putFile(String smbPath, File file) throws IOException {

        try {
            SmbFile dir = new SmbFile(this.address + smbPath + file.getName());
            dir.createNewFile();

            Path source = Paths.get(file.getPath());

            OutputStream out = dir.getOutputStream();
            Files.copy(source, out);

        } catch (SmbException e) {
            TouchDaemon.LOGGER.log(Level.WARNING, "Can't create file " + smbPath + ".", e);
        } catch (MalformedURLException e) {

        }
    }

    public void clearFolder(String smbPath) {

        try {
            SmbFile[] files = new SmbFile(address + smbPath).listFiles();

            //System.out.println(address + smbPath);
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }
        } catch (SmbException e) {
            System.err.println("Can't clear. " + e.getMessage());
        } catch (MalformedURLException e) {

        }
    }
    
    public SmbFile getFile(String smbPath) {

        try {
            SmbFile smbFile = new SmbFile(address + smbPath, this.getNtlmPasswordAuthentication());
            
            return smbFile;
            
        } catch (MalformedURLException e) {

        }
        
        return null;
    }
}
