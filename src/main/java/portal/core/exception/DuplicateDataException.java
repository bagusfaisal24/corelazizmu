package portal.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data Duplicate")
@NoArgsConstructor
public class DuplicateDataException extends RuntimeException {

    @Getter
    @Setter
    private String message = "Data Duplicate";

    public DuplicateDataException(String message) {
        super();
        this.message = message;
    }
}