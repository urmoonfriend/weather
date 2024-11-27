package tech.jusan.weather.clients.models.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Location {
    private String name;
    private String region;
    private String country;
    private BigDecimal lat;
    private BigDecimal lon;
    @JsonProperty("tz_id")
    private String timezone;
    @JsonProperty("localtime_epoch")
    private Long localTimeEpoch;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime localtime;
}
