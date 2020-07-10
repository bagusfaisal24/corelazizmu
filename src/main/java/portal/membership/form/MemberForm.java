package portal.membership.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import portal.util.LocalDateDeserializer;
import portal.util.LocalDateSerializer;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static portal.util.message_error.MessageErrorConst.DATA_TIDAK_BOLEH_KOSONG;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberForm {
    
    @NotEmpty(message = DATA_TIDAK_BOLEH_KOSONG)
    private String name;

    private String dobPlace;
    
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    
    @NotEmpty(message = DATA_TIDAK_BOLEH_KOSONG)
    private String job;
    
    @NotEmpty(message = DATA_TIDAK_BOLEH_KOSONG)
    private String nbm;

    private Long rantingId;

}
