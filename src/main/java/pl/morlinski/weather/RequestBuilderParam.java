package pl.morlinski.weather;

import pl.morlinski.weather.openweathermap.RequestBuilder.Param;

public interface RequestBuilderParam {
    RequestBuilderParam addParam(Param param, String value);

    RequestBuilderExecutor build();
}
