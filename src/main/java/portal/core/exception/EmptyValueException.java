package portal.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "empty value")
@NoArgsConstructor
public class EmptyValueException extends RuntimeException {

    @Getter
    @Setter
    private String message = "Data Not Found";

    public EmptyValueException(String message) {
        super();
        this.message = message;
    }

}
