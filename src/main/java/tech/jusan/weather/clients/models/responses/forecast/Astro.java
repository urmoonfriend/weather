package tech.jusan.weather.clients.models.responses.forecast;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalTime;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Astro {
    @JsonFormat(pattern = "hh:mm a")
    private LocalTime sunrise;
    @JsonFormat(pattern = "hh:mm a")
    private LocalTime sunset;
    @JsonFormat(pattern = "hh:mm a")
    private LocalTime moonrise;
    @JsonFormat(pattern = "hh:mm a")
    private LocalTime moonset;
    private String moonPhase;
    private String moonIllumination;
}
