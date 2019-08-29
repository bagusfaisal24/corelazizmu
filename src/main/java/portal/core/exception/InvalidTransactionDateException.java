package portal.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "invalid transaction date")
@NoArgsConstructor
public class InvalidTransactionDateException extends RuntimeException {

    @Getter
    private final String message = "invalid transaction date";

}
