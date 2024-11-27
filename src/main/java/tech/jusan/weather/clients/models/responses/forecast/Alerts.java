package tech.jusan.weather.clients.models.responses.forecast;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Alerts {
    private List<Alert> alert;
}
