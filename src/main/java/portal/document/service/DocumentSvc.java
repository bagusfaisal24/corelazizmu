package portal.document.service;

import jcifs.smb1.smb1.SmbFile;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import portal.util.file.FileSmbSvc;

import java.io.IOException;

@Service
public class DocumentSvc {

    @Value("${app.ops.location:}")
    @Setter
    private String location;

    @Value("${app.ops.domain:}")
    @Setter
    private String domain;

    @Value("${app.ops.username:}")
    @Setter
    private String username;

    @Value("${app.ops.password:}")
    @Setter
    private String password;

    @Autowired
    FileSmbSvc fileSmbSvc;

    public void writeFile(MultipartFile file) throws IOException {
        String newDir = String.format("%s/%s", location, "document");
        String filePath = String.format("%s/%s/%s", location, "document", file.getOriginalFilename());

        SmbFile smbFile = fileSmbSvc.getaAthentication(filePath, username, password, domain);
        SmbFile newDirFile = fileSmbSvc.getaAthentication(newDir, username, password, domain);

        byte[] bytes = file.getBytes();
        // cek available path root folder
        if (!fileSmbSvc.isExist(newDirFile)) {
            fileSmbSvc.write(bytes, smbFile);
        }
        fileSmbSvc.write(bytes, smbFile);
    }
}
