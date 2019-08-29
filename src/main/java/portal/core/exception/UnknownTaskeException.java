package portal.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "unknown task operation")
@NoArgsConstructor
public class UnknownTaskeException extends RuntimeException {

    @Getter
    private final String message = "unknown task operation";

}
