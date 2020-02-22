package portal.document.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import portal.document.service.DocumentSvc;

import java.io.IOException;

@RestController
@RequestMapping("/v1/master/document")
public class DocumenController {

    @Autowired
    private DocumentSvc documentSvc;

    @RequestMapping(method = RequestMethod.POST)
    public void write(@RequestParam(value = "file") MultipartFile file) throws IOException {
        documentSvc.writeFile(file);
    }

}
