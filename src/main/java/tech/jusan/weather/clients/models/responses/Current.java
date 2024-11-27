package tech.jusan.weather.clients.models.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@SuppressWarnings("all")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Current {
    private Long lastUpdatedEpoch;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastUpdated;
    private Float tempC;
    private Float tempF;
    private Integer isDay;
    private Condition condition;
    private Float windMph;
    private Float windKph;
    private Integer windDegree;
    private String windDir;
    private Float pressureMb;
    private Float pressureIn;
    private Float precipMm;
    private Float precipIn;
    private Integer humidity;
    private Integer cloud;
    private Float feelslikeC;
    private Float feelslikeF;
    private Float windchillC;
    private Float windchillF;
    private Float heatindexC;
    private Float heatindexF;
    private Float dewpointC;
    private Float dewpointF;
    private Float visKm;
    private Float visMiles;
    private Float uv;
    private Float gustMph;
    private Float gustKph;
    private AirQuality airQuality;
}
