package tech.jusan.weather.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table(name = "city", schema = "tech_jusan_weather")
@Entity(name = "city")
@Accessors(chain = true)
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String timezone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
