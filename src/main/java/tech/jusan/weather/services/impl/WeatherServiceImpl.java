package tech.jusan.weather.services.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.jusan.weather.clients.WeatherApiClient;
import tech.jusan.weather.clients.models.responses.CurrentResponse;
import tech.jusan.weather.clients.models.responses.forecast.ForecastResponse;
import tech.jusan.weather.exceptions.AuthException;
import tech.jusan.weather.exceptions.CityNotFoundException;
import tech.jusan.weather.exceptions.WeatherApiUnavailableException;
import tech.jusan.weather.models.dtos.ResultMessage;
import tech.jusan.weather.services.WeatherService;
import tech.jusan.weather.services.db.CityDatabase;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService {
    private final WeatherApiClient weatherApiClient;
    private final CityDatabase cityDatabase;

    @Override
    public ResponseEntity<ResultMessage<CurrentResponse>> getWeatherById(Long id) {
        return cityDatabase.findById(id)
                .map(city -> getWeatherByName(city.getName()))
                .orElseGet(() -> ResultMessage.notFound(
                                new CityNotFoundException(id).getMessage()
                        )
                );
    }

    @Override
    public ResponseEntity<ResultMessage<ForecastResponse>> getForecastById(Long id, Integer days) {
        return cityDatabase.findById(id)
                .map(city -> getForecastByNameAndDays(city.getName(), days))
                .orElseGet(() -> ResultMessage.notFound(
                                new CityNotFoundException(id).getMessage()
                        )
                );
    }

    private ResponseEntity<ResultMessage<CurrentResponse>> getWeatherByName(String name) {
        ResponseEntity<ResultMessage<CurrentResponse>> response = ResultMessage.serverError();
        try {
            ResponseEntity<CurrentResponse> result = weatherApiClient.getCurrentWeather(name);
            if (result != null && HttpStatusCode.valueOf(200).equals(result.getStatusCode())) {
                response = ResultMessage.success(result.getBody());
            }
        } catch (FeignException.ServiceUnavailable e) {
            response = ResultMessage.serviceUnavailable(
                    new WeatherApiUnavailableException().getMessage()
            );
        } catch (FeignException.Unauthorized | FeignException.Forbidden e) {
            response = ResultMessage.authError(
                    new AuthException().getMessage()
            );
        }
        return response;
    }

    private ResponseEntity<ResultMessage<ForecastResponse>> getForecastByNameAndDays(String name, Integer days) {
        ResponseEntity<ResultMessage<ForecastResponse>> response = ResultMessage.unknownError();
        try {
            ResponseEntity<ForecastResponse> result = weatherApiClient.getForecast(name, days);
            if (result != null && HttpStatusCode.valueOf(200).equals(result.getStatusCode())) {
                response = ResultMessage.success(result.getBody());
            }
        } catch (FeignException.ServiceUnavailable e) {
            response = ResultMessage.serviceUnavailable(
                    new WeatherApiUnavailableException().getMessage()
            );
        } catch (FeignException.Unauthorized | FeignException.Forbidden e) {
            response = ResultMessage.authError(
                    new AuthException().getMessage()
            );
        }
        return response;
    }
}
