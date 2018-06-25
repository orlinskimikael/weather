package pl.morlinski.weather;

import java.time.LocalDateTime;

public interface Weather {
    double rain(LocalDateTime begin, LocalDateTime end);
    double cloud(LocalDateTime begin, LocalDateTime end);
    double temperature(LocalDateTime begin, LocalDateTime end);
}