package portal.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import portal.core.exception.MissingFileException;

import java.io.File;
import java.nio.file.FileSystemNotFoundException;

public class SmartLoader {

    private static final Logger log = LoggerFactory.getLogger(SmartLoader.class);

    public static Resource smartLoad(String templatePath) {
        boolean isInternalResource = false;
        if (!templatePath.equals("") && templatePath.substring(0, 1).equals("/")) isInternalResource = true;
        return loadFile(templatePath, isInternalResource);
    }

    public static Resource loadFile(String templatePath) {
        return loadFile(templatePath, false);
    }

    public static Resource loadFile(String templatePath, boolean isInternalResource) {
        if (isInternalResource) {
            Resource resource;
            try {
                resource = new ClassPathResource(templatePath);
            } catch (FileSystemNotFoundException e) {
                MDC.put("read-file", templatePath);
                MDC.put("error", e.getMessage());
                log.warn("failed to read file ");
                MDC.remove("read-file");
                MDC.remove("error");
                throw e;
            }
            return resource;
        }

        Resource resource;
        try {
            File file = new File(templatePath);
            resource = new FileSystemResource(file);
        } catch (FileSystemNotFoundException e) {
            MDC.put("read-file", templatePath);
            MDC.put("error", e.getMessage());
            log.warn("failed to read file ");
            MDC.remove("read-file");
            MDC.remove("error");
            throw new MissingFileException(String.format("file not found: %s", e.getMessage()));
        }

        return resource;
    }

}
