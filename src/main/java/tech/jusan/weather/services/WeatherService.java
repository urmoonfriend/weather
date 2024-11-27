package tech.jusan.weather.services;

import org.springframework.http.ResponseEntity;
import tech.jusan.weather.clients.models.responses.CurrentResponse;
import tech.jusan.weather.clients.models.responses.forecast.ForecastResponse;
import tech.jusan.weather.models.dtos.ResultMessage;

public interface WeatherService {
    ResponseEntity<ResultMessage<CurrentResponse>> getWeatherById(Long id);

    ResponseEntity<ResultMessage<ForecastResponse>> getForecastById(Long id, Integer days);
}
