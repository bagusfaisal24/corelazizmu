package portal.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "unknown file mime")
@NoArgsConstructor
public class UnknownFileMIMEException extends RuntimeException {

    @Getter
    @Setter
    private String message = "unknown file mime";

    public UnknownFileMIMEException(String message) {
        super();
        this.message = message;
    }

}
