package portal.util.file;

import jcifs.smb1.smb1.SmbFile;

import java.io.IOException;
import java.net.MalformedURLException;

public interface FileSmbSvc {

    void write(byte[] data, SmbFile smbFile) throws IOException;

    byte[] read(SmbFile smbFile) throws IOException;

    SmbFile getaAthentication(String filePath, String username, String password, String domain) throws MalformedURLException;
}
