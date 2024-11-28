package tech.jusan.weather.services.db.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jusan.weather.models.dtos.CityDto;
import tech.jusan.weather.models.entities.City;
import tech.jusan.weather.repositories.CityRepository;
import tech.jusan.weather.services.db.CityDatabase;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@CacheConfig(cacheNames = "cities")
public class CityDatabaseImpl implements CityDatabase {
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<City> create(CityDto cityDto) {
        City city = cityRepository.save(
                modelMapper.map(cityDto, City.class)
        );
        return Optional.of(city);
    }

    @Override
    @Transactional
    public Optional<City> update(CityDto cityDto) {
        return cityRepository.findById(cityDto.getId())
                .flatMap(city -> {
                    cityRepository.update(
                            cityDto.getId(),
                            cityDto.getName(),
                            cityDto.getLatitude(),
                            cityDto.getLongitude(),
                            cityDto.getTimezone()
                    );
                    return Optional.of(modelMapper.map(cityDto, City.class));
                });
    }

    @Override
    @Cacheable(value = "city", key = "#id")
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    @Cacheable(value = "cityList")
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    @Transactional
    @CacheEvict(value = "city", key = "#id")
    public Optional<City> deleteById(Long id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        optionalCity.ifPresent(city -> cityRepository.deleteById(city.getId()));
        return optionalCity;
    }

}
