package tech.jusan.weather.clients.models.responses.forecast;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ForecastDay {
    private LocalDate date;
    private Long dateEpoch;
    private Day day;
    private Astro astro;
    private List<CurrentHour> hour;
}
