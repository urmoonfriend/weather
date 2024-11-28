package tech.jusan.weather.services.db;

import org.springframework.transaction.annotation.Transactional;
import tech.jusan.weather.models.dtos.CityDto;
import tech.jusan.weather.models.entities.City;

import java.util.List;
import java.util.Optional;

public interface CityDatabase {

    Optional<City> create(CityDto cityDto);

    @Transactional
    Optional<City> update(CityDto cityDto);

    Optional<City> findById(Long id);

    Optional<City> deleteById(Long id);

    List<City> findAll();
}
