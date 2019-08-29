package portal.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "invalid action")
@NoArgsConstructor
public class InvalidActionException extends RuntimeException {

    @Getter
    @Setter
    private String message = "invalid action";

    public InvalidActionException(String message) {
        super();
        this.message = message;
    }

}
