package portal.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Status")
@NoArgsConstructor
public class InvalidStatusException extends RuntimeException {

    @Getter
    @Setter
    private String message = "Invalid Status";

    public InvalidStatusException(String message) {
        super();
        this.message = message;
    }

}
