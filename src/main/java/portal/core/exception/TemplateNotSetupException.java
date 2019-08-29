package portal.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "missing template setup")
@NoArgsConstructor
public class TemplateNotSetupException extends RuntimeException {

    @Getter
    @Setter
    private String message = "missing template setup";

    public TemplateNotSetupException(String message) {
        super();
        this.message = message;
    }

}
