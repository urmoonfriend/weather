package tech.jusan.weather.clients.models.responses;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Condition {
    private String text;
    private String icon;
    private Integer code;
}
