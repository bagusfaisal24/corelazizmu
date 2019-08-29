package portal.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Invalid Data")  // 400
public class ValidationErrorException extends RuntimeException {

    public static final String message = "Invalid Data";

    @Getter
    private List<ObjectError> errors;

    public ValidationErrorException(List<ObjectError> errors) {
        super();
        this.errors = errors;
    }

}