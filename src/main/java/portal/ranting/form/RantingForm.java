package portal.ranting.form;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RantingForm {

    @NotNull
    private String name;

    @NotNull
    private String ketuaRanting;
}
