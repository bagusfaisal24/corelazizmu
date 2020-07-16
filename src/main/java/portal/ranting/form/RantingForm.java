package portal.ranting.form;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

import static portal.util.message_error.MessageErrorConst.DATA_TIDAK_BOLEH_KOSONG;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RantingForm {

    @NotEmpty(message = DATA_TIDAK_BOLEH_KOSONG)
    private String name;
    
    @NotEmpty(message = DATA_TIDAK_BOLEH_KOSONG)
    private String ketuaRanting;
}
