package pl.morlinski.weather;

import pl.morlinski.weather.openweathermap.RequestBuilder.Operation;

/**
 * Pierwszy etep budowania zapytania. Wybór operacji do wykonania.
 * 
 * @author Michał Orliński
 * @since 2018-06-25
 */
public interface RequestBuilderOperation {
    RequestBuilderParam setOperation(Operation operation);
}
