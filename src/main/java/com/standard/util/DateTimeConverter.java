package com.standard.util;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Component
public class DateTimeConverter {

    public OffsetDateTime convert(LocalDateTime localDateTime) {
        // Get the system's default time zone
        ZoneId systemZone = ZoneId.systemDefault();

        // Get the offset from the system's time zone
        ZoneOffset systemOffset = systemZone.getRules().getOffset(localDateTime);

        // Convert LocalDateTime to OffsetDateTime using the system's offset
        return localDateTime.atOffset(systemOffset);
    }
}
