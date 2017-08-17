package network;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import jcifs.smb.NtlmAuthenticator;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbAuthException;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

/**
 *
 * @author Ivan
 */
public class SMBClient extends NtlmAuthenticator {

    private String address;
    private String username, password;

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
