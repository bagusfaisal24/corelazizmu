package portal.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class DataNotFoundException extends RuntimeException {

    @Getter
    @Setter
    private String message = "Data Not Found";
    
    public DataNotFoundException(String message) {
        super();
        this.message = message;
    }
}
