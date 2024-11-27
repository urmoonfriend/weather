package tech.jusan.weather.clients.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class AirQuality {
    private BigDecimal co;
    private BigDecimal no2;
    private BigDecimal o3;
    private BigDecimal so2;
    private BigDecimal pm2_5;
    private BigDecimal pm10;
    @JsonProperty("us-epa-index")
    private Integer usEpaIndex;
    @JsonProperty("gb-defra-index")
    private Integer gbDefraIndex;
}
