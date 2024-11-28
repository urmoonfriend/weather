package tech.jusan.weather.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tech.jusan.weather.models.entities.City;

import java.math.BigDecimal;

public interface CityRepository extends JpaRepository<City, Long> {

    @Modifying
    @Query(value = "update City c " +
            "set c.name = :name, " +
            "c.latitude = :latitude, " +
            "c.longitude = :longitude, " +
            "c.timezone = :timezone, " +
            "c.updatedAt = CURRENT_TIMESTAMP " +
            "where c.id = :id ")
    void update(Long id, String name, BigDecimal latitude, BigDecimal longitude, String timezone);

}
