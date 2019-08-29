package portal.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Inconsistent Data")
@NoArgsConstructor
public class InconsistentDataException extends RuntimeException {

    @Getter
    @Setter
    private String message = "Inconsistent Data";

    public InconsistentDataException(String message) {
        super();
        this.message = message;
    }
}