package tech.jusan.weather.clients.models.responses.forecast;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;
import tech.jusan.weather.clients.models.responses.Condition;

@Data
@Accessors(chain = true)
@SuppressWarnings("all")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Day {
    private Float maxtempC;
    private Float maxtempF;
    private Float mintempC;
    private Float mintempF;
    private Float avgtempC;
    private Float avgtempF;
    private Float maxwindMph;
    private Float maxWindKph;
    private Integer totalprecipMm;
    private Integer totalprecipIn;
    private Integer avgvisKm;
    private Integer avgvisMiles;
    private Integer avgHumidity;
    private Integer dailyWillItRain;
    private Integer dailyChanceOfRain;
    private Integer dailyWillItSnow;
    private Integer dailyChanceOfSnow;
    private Condition condition;
    private Integer uv;
}
