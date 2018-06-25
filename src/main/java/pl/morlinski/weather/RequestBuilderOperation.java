package pl.morlinski.weather;

import pl.morlinski.weather.openweathermap.RequestBuilder.Operation;

public interface RequestBuilderOperation {
    RequestBuilderParam setOperation(Operation operation);
}
