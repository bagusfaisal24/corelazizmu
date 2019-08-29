package portal.util;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDate;
import java.time.LocalDateTime;

class LocalDateModule extends SimpleModule {
    private static final String NAME = "LocalDateModule";
    private static final VersionUtil VERSION_UTIL = new VersionUtil() {};

    LocalDateModule() {
        super(NAME, VERSION_UTIL.version());
        addSerializer(LocalDate.class, new LocalDateSerializer());
        addDeserializer(LocalDate.class, new LocalDateDeserializer());
        addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    }
}
