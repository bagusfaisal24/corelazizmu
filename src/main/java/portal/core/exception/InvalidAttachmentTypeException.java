package portal.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Attachment Type")
public class InvalidAttachmentTypeException extends RuntimeException {

    @Getter
    private final String message = "Invalid Attachment Type";

    public InvalidAttachmentTypeException() {
        super();
    }
}
