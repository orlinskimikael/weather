package pl.morlinski.weather;

import java.time.LocalDateTime;

/**
 * Zunifikowany interfejs pogody.
 * 
 * @author Michał Orliński
 * @since 2018-06-25
 */
public interface Weather {
    double rain(LocalDateTime begin, LocalDateTime end);

    double cloud(LocalDateTime begin, LocalDateTime end);

    double temperature(LocalDateTime begin, LocalDateTime end);
}