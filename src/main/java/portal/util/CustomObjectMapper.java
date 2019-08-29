package portal.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        registerModule(new LocalDateModule());
    }

}
