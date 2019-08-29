package portal.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "template file not found")
@NoArgsConstructor
@SuppressWarnings("unused")
public class TemplateNotFoundException extends RuntimeException {

    @Getter
    @Setter
    private String message = "template file not found";

    public TemplateNotFoundException(String message) {
        super();
        this.message = message;
    }

}
