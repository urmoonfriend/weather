
package tech.jusan.weather.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@Validated
public class CityDto {
    private Long id;
    @NotEmpty(message = "name must not be empty")
    private String name;
    @NotNull(message = "latitude must not be null")
    private BigDecimal latitude;
    @NotNull(message = "longitude must not be null")
    private BigDecimal longitude;
    @NotEmpty(message = "timezone must not be empty")
    private String timezone;
}
