package pl.morlinski.weather;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class DataConverter {
    public static LocalDateTime convertTimestampToLocalDataTime(long timestamp) {
        LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp),
                TimeZone.getDefault().toZoneId());
        return time;
    }
}
