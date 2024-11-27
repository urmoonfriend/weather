package tech.jusan.weather.clients.models.responses.forecast;

import lombok.Data;
import lombok.experimental.Accessors;
import tech.jusan.weather.clients.models.responses.Current;
import tech.jusan.weather.clients.models.responses.Location;

@Data
@Accessors(chain = true)
public class ForecastResponse {
    private Location location;
    private Current current;
    private Forecast forecast;
    private Alert alerts;
}
