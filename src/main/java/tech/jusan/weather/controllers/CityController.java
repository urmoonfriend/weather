package tech.jusan.weather.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.jusan.weather.models.dtos.CityDto;
import tech.jusan.weather.models.dtos.ResultMessage;
import tech.jusan.weather.models.entities.City;
import tech.jusan.weather.services.db.CityDatabase;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/city")
@Tag(name = "City endpoint", description = "Методы для администрирования городов")
@Validated
public class CityController {
    private final CityDatabase cityDatabase;

    @PostMapping
    @Tag(name = "Create city", description = "Запрос на создание города")
    public ResponseEntity<ResultMessage<City>> addCity(@RequestBody @Valid CityDto cityDto) {
        return cityDatabase.create(cityDto)
                .map(ResultMessage::success)
                .orElseGet(ResultMessage::serverError);
    }

    @PutMapping("/{id}")
    @Tag(name = "Update city", description = "Запрос на обновление города")
    public ResponseEntity<ResultMessage<City>> updateCity(@RequestBody @Valid CityDto cityDto, @PathVariable Long id) {
        return cityDatabase.update(cityDto.setId(id))
                .map(ResultMessage::success)
                .orElseGet(ResultMessage::serverError);
    }

    @GetMapping("/{id}")
    @Tag(name = "Get city", description = "Запрос на получение города")
    public ResponseEntity<ResultMessage<City>> getCity(@PathVariable Long id) {
        return cityDatabase.findById(id)
                .map(ResultMessage::success)
                .orElseGet(ResultMessage::serverError);
    }

    @GetMapping
    @Tag(name = "Get cities", description = "Запрос на получение городов")
    public ResponseEntity<ResultMessage<List<City>>> getCities() {
        return ResultMessage.success(cityDatabase.findAll());
    }

    @DeleteMapping("/{id}")
    @Tag(name = "Delete city", description = "Запрос на удаление города")
    public ResponseEntity<ResultMessage<City>> deleteCity(@PathVariable Long id) {
        return cityDatabase.deleteById(id)
                .map(ResultMessage::success)
                .orElseGet(ResultMessage::serverError);
    }
}
