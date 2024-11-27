package tech.jusan.weather.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.jusan.weather.clients.models.responses.CurrentResponse;
import tech.jusan.weather.clients.models.responses.forecast.ForecastResponse;
import tech.jusan.weather.configs.FeignClientConfig;

@FeignClient(name = "weather-api", url = "${app.clients.weather.url}",
        configuration = FeignClientConfig.class)
public interface WeatherApiClient {

    @GetMapping("/current.json")
    ResponseEntity<CurrentResponse> getCurrentWeather(
            @RequestParam(name = "q") String city
    );

    @GetMapping("/forecast.json")
    ResponseEntity<ForecastResponse> getForecast(
            @RequestParam(name = "q") String city,
            @RequestParam(name ="days") Integer days
    );
}
