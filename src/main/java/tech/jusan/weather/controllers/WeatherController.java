package tech.jusan.weather.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jusan.weather.clients.models.responses.CurrentResponse;
import tech.jusan.weather.clients.models.responses.forecast.ForecastResponse;
import tech.jusan.weather.models.dtos.ResultMessage;
import tech.jusan.weather.services.WeatherService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
@Slf4j
@Tag(name = "Weather endpoint", description = "Методы для просмотра погоды по городу")

public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/current/city/{id}")
    @Tag(name = "Get current weather by city id",
            description = "Запрос на получение информации по текущей погоде по id города")
    public ResponseEntity<ResultMessage<CurrentResponse>> getWeatherByCity(@PathVariable Long id) {
        return weatherService.getWeatherById(id);
    }

    @GetMapping("/forecast/city/{id}")
    @Tag(name = "Get forecast weather by city id and days",
            description = "Запрос на получение информации по погоде на несколько дней по id города")
    public ResponseEntity<ResultMessage<ForecastResponse>> getWeatherByCityForSeveralDates(
            @PathVariable Long id,
            @RequestParam("days") Integer days) {
        return weatherService.getForecastById(id, days);
    }
}
