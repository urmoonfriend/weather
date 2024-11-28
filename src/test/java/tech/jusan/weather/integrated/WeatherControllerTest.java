package tech.jusan.weather.integrated;

import feign.FeignException;
import feign.Request;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import tech.jusan.weather.EnvConfigTest;
import tech.jusan.weather.clients.WeatherApiClient;
import tech.jusan.weather.clients.models.responses.Condition;
import tech.jusan.weather.clients.models.responses.Current;
import tech.jusan.weather.clients.models.responses.CurrentResponse;
import tech.jusan.weather.clients.models.responses.Location;
import tech.jusan.weather.clients.models.responses.forecast.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WeatherControllerTest extends EnvConfigTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WeatherApiClient weatherApiClient;
    private CurrentResponse currentResponse;
    private Current current;
    private Location location;
    private Condition condition;
    private ForecastResponse forecastResponse;
    private Astro astro;
    private Day day;
    private ForecastDay forecastDay1;
    private Forecast forecast;

    @BeforeEach
    public void setup() {
        condition = new Condition()
                .setText("Overcast")
                .setIcon("//cdn.weatherapi.com/weather/64x64/night/122.png")
                .setCode(1009);
        current = new Current()
                .setLastUpdatedEpoch(1732796100L)
                .setLastUpdated(LocalDateTime.parse("2024-11-28T17:15"))
                .setTempC(6.2f)
                .setTempF(43.2f)
                .setIsDay(0)
                .setCondition(condition)
                .setWindMph(2.9f)
                .setWindKph(4.7f)
                .setWindDegree(191)
                .setWindDir("SSW")
                .setPressureMb(1015.0f)
                .setPressureIn(29.97f)
                .setPrecipMm(0.0f)
                .setPrecipIn(0.0f)
                .setHumidity(52)
                .setCloud(50)
                .setFeelslikeC(5.6f)
                .setFeelslikeF(42.0f)
                .setWindchillC(8.0f)
                .setWindchillF(46.4f)
                .setHeatindexC(8.4f)
                .setHeatindexF(47.1f)
                .setDewpointC(-5.9f)
                .setDewpointF(21.5f)
                .setVisKm(5.0f)
                .setVisMiles(3.0f)
                .setUv(0.0f)
                .setGustMph(5.2f)
                .setGustKph(8.3f)
                .setAirQuality(null);
        currentResponse = new CurrentResponse()
                .setCurrent(current)
                .setLocation(location);
        location = new Location()
                .setName("Almaty")
                .setRegion("Almaty City")
                .setCountry("Kazakhstan")
                .setLat(new BigDecimal("43.25"))
                .setLon(new BigDecimal("76.95"))
                .setTimezone("Asia/Almaty")
                .setLocalTimeEpoch(1732796141L)
                .setLocaltime(LocalDateTime.parse("2024-11-28T17:15"));
        currentResponse = new CurrentResponse()
                .setCurrent(current)
                .setLocation(location);
        astro = new Astro()
                .setSunrise("08:01 AM")
                .setSunset("05:19 PM")
                .setMoonrise("05:01 AM")
                .setMoonset("03:26 PM")
                .setMoonPhase("Waning Crescent")
                .setMoonIllumination("10");
        day = new Day()
                .setMaxtempC(11.4f)
                .setMaxtempF(52.6f)
                .setMintempC(4.9f)
                .setMintempF(40.8f)
                .setAvgtempC(7.5f)
                .setAvgtempF(45.4f)
                .setMaxwindMph(5.4f)
                .setTotalprecipMm(0)
                .setTotalprecipIn(0)
                .setAvgvisKm(10)
                .setAvgvisMiles(6)
                .setDailyWillItRain(0)
                .setDailyChanceOfRain(0)
                .setDailyWillItSnow(0)
                .setDailyChanceOfSnow(0)
                .setCondition(condition)
                .setUv(0);
        forecastDay1 = new ForecastDay()
                .setDate(LocalDate.parse("2024-11-28"))
                .setDateEpoch(1732752000L)
                .setDay(day)
                .setAstro(astro)
                .setHour(Collections.emptyList());
        forecast = new Forecast()
                .setForecastday(Collections.singletonList(forecastDay1));
        forecastResponse = new ForecastResponse()
                .setLocation(location)
                .setCurrent(current)
                .setForecast(forecast)
                .setAlerts(null);
    }

    @Test
    public void whenGetCurrentThenOk() throws Exception {
        when(weatherApiClient.getCurrentWeather(any()))
                .thenReturn(ResponseEntity.ok(currentResponse));

        mockMvc.perform(get("/weather/current/city/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.code", Is.is("OK")))
                .andExpect(jsonPath("$.message.location.country", Is.is("Kazakhstan")))
                .andExpect(jsonPath("$.message.location.name", Is.is("Almaty")))
                .andExpect(jsonPath("$.message.current.temp_c", Is.is(6.2)))
                .andExpect(jsonPath("$.message.current.wind_mph", Is.is(2.9)));
    }

    @Test
    public void whenGetCurrentThenCityNotFound() throws Exception {
        when(weatherApiClient.getCurrentWeather(any()))
                .thenReturn(ResponseEntity.ok(currentResponse));

        mockMvc.perform(get("/weather/current/city/123123123"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success", Is.is(false)))
                .andExpect(jsonPath("$.code", Is.is("NOT_FOUND")))
                .andExpect(jsonPath("$.message").doesNotExist())
                .andExpect(jsonPath("$.error", Is.is("City with id: 123123123 not found")));
    }

    @Test
    public void whenGetCurrentThenAuthError() throws Exception {
        FeignException.Unauthorized unauthorizedException =
                new FeignException.Unauthorized(
                        "Unauthorized", Request.create(Request.HttpMethod.GET, "/api/weather",
                        Map.of(), null, null, null), null, null);

        when(weatherApiClient.getCurrentWeather(any())).thenThrow(unauthorizedException);


        mockMvc.perform(get("/weather/current/city/1"))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.success", Is.is(false)))
                .andExpect(jsonPath("$.code", Is.is("AUTH_ERROR")))
                .andExpect(jsonPath("$.message").doesNotExist())
                .andExpect(jsonPath("$.error", Is.is("Authentication failed. Please check the API key.")));
    }

    @Test
    public void whenGetForecastThenOk() throws Exception {
        when(weatherApiClient.getForecast(any(), any()))
                .thenReturn(ResponseEntity.ok(forecastResponse));

        mockMvc.perform(get("/weather/forecast/city/1?days=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.code", Is.is("OK")))
                .andExpect(jsonPath("$.message.location.name", Is.is("Almaty")))
                .andExpect(jsonPath("$.message.location.region", Is.is("Almaty City")))
                .andExpect(jsonPath("$.message.location.country", Is.is("Kazakhstan")))
                .andExpect(jsonPath("$.message.location.lat", Is.is(43.25)))
                .andExpect(jsonPath("$.message.location.lon", Is.is(76.95)))
                .andExpect(jsonPath("$.message.location.tz_id", Is.is("Asia/Almaty")))
                .andExpect(jsonPath("$.message.location.localtime_epoch", Is.is(1732796141)))
                .andExpect(jsonPath("$.message.location.localtime", Is.is("2024-11-28 17:15")));
    }

    @Test
    public void whenGetForecastThenCityNotFound() throws Exception {
        when(weatherApiClient.getForecast(any(), any()))
                .thenReturn(ResponseEntity.ok(forecastResponse));

        mockMvc.perform(get("/weather/forecast/city/123123?days=2"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success", Is.is(false)))
                .andExpect(jsonPath("$.code", Is.is("NOT_FOUND")))
                .andExpect(jsonPath("$.message").doesNotExist())
                .andExpect(jsonPath("$.error", Is.is("City with id: 123123 not found")));
    }

    @Test
    public void whenGetForecastThenServiceUnavailable() throws Exception {
        FeignException.ServiceUnavailable serviceUnavailable =
                new FeignException.ServiceUnavailable("Unavailable",
                        Request.create(Request.HttpMethod.GET, "/api/weather",
                                Map.of(), null, null, null), null, Map.of());

        when(weatherApiClient.getForecast(any(), any())).thenThrow(serviceUnavailable);


        mockMvc.perform(get("/weather/forecast/city/1?days=2"))
                .andDo(print())
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.success", Is.is(false)))
                .andExpect(jsonPath("$.code", Is.is("SERVICE_UNAVAILABLE")))
                .andExpect(jsonPath("$.message").doesNotExist())
                .andExpect(jsonPath("$.error",
                        Is.is("Weather API is currently unavailable. Please try again later.")));
    }

}
