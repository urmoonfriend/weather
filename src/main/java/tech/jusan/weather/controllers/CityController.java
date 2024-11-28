package tech.jusan.weather.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import tech.jusan.weather.exceptions.CityNotFoundException;
import tech.jusan.weather.models.dtos.CityDto;
import tech.jusan.weather.models.dtos.ResultMessage;
import tech.jusan.weather.models.entities.City;
import tech.jusan.weather.services.db.CityDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
@Tag(name = "City endpoint", description = "Методы для администрирования городов")
@Validated
public class CityController {
    private final CityDatabase cityDatabase;

    @PostMapping
    @Operation(summary = "Create city", description = "Запрос на создание города")
    public ResponseEntity<ResultMessage<City>> addCity(@RequestBody @Valid CityDto cityDto) {
        return cityDatabase.create(cityDto)
                .map(ResultMessage::success)
                .orElseGet(ResultMessage::serverError);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update city", description = "Запрос на обновление города")
    public ResponseEntity<ResultMessage<City>> updateCity(@RequestBody @Valid CityDto cityDto, @PathVariable Long id) {
        return cityDatabase.update(cityDto.setId(id))
                .map(ResultMessage::success)
                .orElseGet(() -> ResultMessage.notFound(
                        new CityNotFoundException(id).getMessage()
                ));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get city", description = "Запрос на получение города")
    public ResponseEntity<ResultMessage<City>> getCity(@PathVariable Long id) {
        return cityDatabase.findById(id)
                .map(ResultMessage::success)
                .orElseGet(() -> ResultMessage.notFound(
                        new CityNotFoundException(id).getMessage()
                ));
    }

    @GetMapping
    @Operation(summary = "Get cities", description = "Запрос на получение городов")
    public ResponseEntity<ResultMessage<List<City>>> getCities() {
        return ResultMessage.success(cityDatabase.findAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete city", description = "Запрос на удаление города")
    public ResponseEntity<ResultMessage<City>> deleteCity(@PathVariable Long id) {
        return cityDatabase.deleteById(id)
                .map(ResultMessage::success)
                .orElseGet(() -> ResultMessage.notFound(
                        new CityNotFoundException(id).getMessage()
                ));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultMessage<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return ResultMessage.error(errors);
    }
}
