package pl.morlinski.weather;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

/**
 * Narzędzia dat.
 * 
 * @author Michał Orliński
 * @since 2018-06-25
 */
public class DataUtils {

    private DataUtils() {
    }

    /**
     * Konwersja daty w postaci znacznika czasu (w sekunach) do obiektu klasy
     * {@link LocalDateTime}
     * 
     * @param timestamp
     *            Znacznik czasu w sekunach.
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime convertTimestampToLocalDataTime(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), TimeZone.getDefault().toZoneId());
    }

    /**
     * Weryfikacja czy data jest pomiędzy wskazanymi datami time należy (begin;end)
     * 
     * @param time
     *            Data do weryfikacji
     * @param begin
     *            Początek okresu
     * @param end
     *            Koniec okresu
     * @return Wynik
     */
    public static boolean timeIsBetween(LocalDateTime time, LocalDateTime begin, LocalDateTime end) {
        return time.isAfter(begin) && time.isBefore(end);
    }
}
