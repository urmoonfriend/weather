package tech.jusan.weather.clients.models.responses;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CurrentResponse {
    private Location location;
    private Current current;
}
