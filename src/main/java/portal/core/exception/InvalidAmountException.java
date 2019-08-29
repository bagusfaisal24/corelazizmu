package portal.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Amount")
@NoArgsConstructor
public class InvalidAmountException extends RuntimeException {

    @Getter
    @Setter
    private String message = "Invalid Amount";

    public InvalidAmountException(String message) {
        super();
        this.message = message;
    }
}
