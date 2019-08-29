package portal.core.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "invalid product type")
@NoArgsConstructor
public class InvalidProductTypeException extends RuntimeException {
}
