package portal.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Missmatch Value")  // 400
@NoArgsConstructor
public class MissmatchValueException extends RuntimeException {

    @Getter
    @Setter
    private String message = "Invalid Status";

    public MissmatchValueException(String message) {
        super();
        this.message = message;
    }

}
