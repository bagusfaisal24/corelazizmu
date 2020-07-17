package portal.zakat.collection.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import portal.util.LocalDateDeserializer;
import portal.util.LocalDateSerializer;
import portal.util.message_error.MessageErrorConst;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZakatForm {
	
	@NotNull (message = MessageErrorConst.DATA_TIDAK_BOLEH_KOSONG)
	private Long memberId;
	
	@NotNull (message = MessageErrorConst.DATA_TIDAK_BOLEH_KOSONG)
	private Long productTypeId;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate submitDate;
	
	@NotNull (message = MessageErrorConst.DATA_TIDAK_BOLEH_KOSONG)
	private Double amount;
	
}
