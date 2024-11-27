package tech.jusan.weather.clients.models.responses.forecast;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
public class Alert {
    private String headline;
    private String category;
    private String event;
    private OffsetDateTime effective;
    private OffsetDateTime expires;
    private String desc;
    private String instruction;
}
