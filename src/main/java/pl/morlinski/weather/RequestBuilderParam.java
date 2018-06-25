package pl.morlinski.weather;

import pl.morlinski.weather.openweathermap.RequestBuilder.Param;

/**
 * Drugi etap budowania zapytania. Ustawienie parametrów zapytania.
 * 
 * @author Michał Orliński
 * @since 2018-06-25
 */
public interface RequestBuilderParam {
    RequestBuilderParam addParam(Param param, String value);

    RequestBuilderExecutor build();
}
