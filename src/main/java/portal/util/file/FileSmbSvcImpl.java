package portal.util.file;

import jcifs.smb1.smb1.NtlmPasswordAuthentication;
import jcifs.smb1.smb1.SmbFile;
import jcifs.smb1.smb1.SmbFileOutputStream;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

@Service("smbFileSvcImpl")
public class FileSmbSvcImpl implements FileSmbSvc {

    @Override
    public void write(byte[] data, SmbFile smbFile) throws IOException {
        SmbFileOutputStream smbFileOutputStream = new SmbFileOutputStream(smbFile);
        smbFileOutputStream.write(data);
        smbFileOutputStream.close();
    }

    @Override
    public byte[] read(SmbFile smbFile) throws IOException {
        InputStream is = smbFile.getInputStream();
        return FileCopyUtils.copyToByteArray(is);
    }

    @Override
    public SmbFile getaAthentication(String filePath, String username, String password, String domain) throws MalformedURLException {
        NtlmPasswordAuthentication authentication = new NtlmPasswordAuthentication(domain, username, password);
        return new SmbFile(filePath, authentication);
    }
}
