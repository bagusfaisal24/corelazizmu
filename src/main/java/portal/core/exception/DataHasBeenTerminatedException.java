package portal.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data Has Been Terminated")
@NoArgsConstructor
public class DataHasBeenTerminatedException extends RuntimeException {

    @Getter
    @Setter
    private String message = "Data Has Been Terminated";

    public DataHasBeenTerminatedException(String message) {
        super();
        this.message = message;
    }
}
