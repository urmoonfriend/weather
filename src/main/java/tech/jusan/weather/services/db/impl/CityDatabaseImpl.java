package tech.jusan.weather.services.db.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
public class CityDatabaseImpl implements CityDatabase {
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<City> create(CityDto cityDto) {
        Optional<City> result = Optional.empty();
        try {
            result = Optional.of(
                    cityRepository.save(
                            modelMapper.map(cityDto, City.class)
                    )
            );
        } catch (Exception e) {
            log.error("CityDatabaseImpl.create error: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    @Transactional
    public Optional<City> update(CityDto cityDto) {
        Optional<City> result = Optional.empty();
        try {
            result = Optional.of(
                    cityRepository.update(cityDto.getId(), cityDto.getName(),
                            cityDto.getLatitude(), cityDto.getLongitude(), cityDto.getTimezone())
            );
        } catch (Exception e) {
            log.error("CityDatabaseImpl.create error: {}", e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<City> deleteById(Long id) {
        return findById(id)
                .map(city -> {
                    cityRepository.deleteById(city.getId());
                    return city;
                });
    }

}
