package portal.core.model;

import lombok.Data;

@Data
public class OkResponse {

    private String message;

    public OkResponse() {
        this.message = "ok";
    }

}
