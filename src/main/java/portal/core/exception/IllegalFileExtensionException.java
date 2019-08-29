package portal.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "illegal file extension")
@NoArgsConstructor
public class IllegalFileExtensionException extends RuntimeException {

    @Getter
    @Setter
    private String message = "illegal file extension";

    public IllegalFileExtensionException(String message) {
        super();
        this.message = message;
    }

}
