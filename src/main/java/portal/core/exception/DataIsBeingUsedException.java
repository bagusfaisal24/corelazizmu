package portal.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data Is Being Used")
@NoArgsConstructor
public class DataIsBeingUsedException extends RuntimeException {

    @Getter
    @Setter
    private String message = "Data Is Being Used";

    public DataIsBeingUsedException(String message) {
        super();
        this.message = message;
    }
}
