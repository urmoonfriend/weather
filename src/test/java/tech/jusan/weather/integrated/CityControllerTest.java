package tech.jusan.weather.integrated;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jusan.weather.EnvConfigTest;
import tech.jusan.weather.models.dtos.CityDto;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerTest extends EnvConfigTest {
    @Autowired
    private MockMvc mockMvc;

    private CityDto cityDto;

    @BeforeEach
    public void setup() {
        cityDto = new CityDto()
                .setName("cityName")
                .setLatitude(BigDecimal.valueOf(0.0))
                .setLongitude(BigDecimal.valueOf(0.0))
                .setTimezone("timezone");
    }

    @Test
    public void whenCreateThenOk() throws Exception {
        mockMvc.perform(post("/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                new ObjectMapper()
                                        .writeValueAsString(cityDto)
                        ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.code", Is.is("OK")))
                .andExpect(jsonPath("$.message.name", Is.is("cityName")))
                .andExpect(jsonPath("$.message.latitude").value("0.0"))
                .andExpect(jsonPath("$.message.longitude").value("0.0"))
                .andExpect(jsonPath("$.message.timezone", Is.is("timezone")));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "| 0.0| 0.0| timezone| name must not be empty",
            "''|0.0|0.0|timezone|name must not be empty",
            "'name'||0.0|timezone|latitude must not be null",
            "'name'|0.0||timezone|longitude must not be null",
            "'name'|0.0|0.0||timezone must not be empty",
            "''|||| timezone must not be empty, " +
                    "latitude must not be null, " +
                    "name must not be empty, " +
                    "longitude must not be null"
    }, delimiter = '|')
    public void whenCreateCityThenValidationError(
            String name, BigDecimal latitude, BigDecimal longitude, String timezone, String expectedResponse) throws Exception {
        mockMvc.perform(
                        post("/cities")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(
                                        cityDto.setName(name)
                                                .setLatitude(latitude)
                                                .setLongitude(longitude)
                                                .setTimezone(timezone)
                                ))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("success", Is.is(false)))
                .andExpect(jsonPath("code", Is.is("VALIDATION_ERROR")))
                .andExpect(jsonPath("error", Is.is(expectedResponse)))
                .andExpect(jsonPath("message").doesNotExist());
    }

    @Test
    public void whenFindByIdThenNotFound() throws Exception {
        mockMvc.perform(get("/cities/123123123"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("success", Is.is(false)))
                .andExpect(jsonPath("$.code", Is.is("NOT_FOUND")))
                .andExpect(jsonPath("$.message").doesNotExist());
    }

    @Test
    public void whenFindByIdThenFound() throws Exception {
        mockMvc.perform(get("/cities/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.code", Is.is("OK")))
                .andExpect(jsonPath("$.message.name", Is.is("Almaty")))
                .andExpect(jsonPath("$.message.latitude").value("43.25"))
                .andExpect(jsonPath("$.message.longitude").value("76.95"))
                .andExpect(jsonPath("$.message.timezone", Is.is("Asia/Almaty")));
    }

    @Test
    public void whenFindAllThenOk() throws Exception {
        mockMvc.perform(get("/cities"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.code", Is.is("OK")));
    }

    @Test
    public void whenUpdateThenOk() throws Exception {
        mockMvc.perform(put("/cities/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                cityDto.setName("newName")
                                        .setLatitude(BigDecimal.valueOf(0.0))
                                        .setLongitude(BigDecimal.valueOf(0.0))
                                        .setTimezone("newTimezone")
                        )))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.code", Is.is("OK")))
                .andExpect(jsonPath("$.message.name", Is.is("newName")))
                .andExpect(jsonPath("$.message.latitude").value("0.0"))
                .andExpect(jsonPath("$.message.longitude").value("0.0"))
                .andExpect(jsonPath("$.message.timezone", Is.is("newTimezone")));
    }

    @Test
    public void whenUpdateThen() throws Exception {
        mockMvc.perform(put("/cities/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                cityDto.setName("newName")
                                        .setLatitude(BigDecimal.valueOf(0.0))
                                        .setLongitude(BigDecimal.valueOf(0.0))
                                        .setTimezone("newTimezone")
                        )))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.code", Is.is("OK")))
                .andExpect(jsonPath("$.message.name", Is.is("newName")))
                .andExpect(jsonPath("$.message.latitude").value("0.0"))
                .andExpect(jsonPath("$.message.longitude").value("0.0"))
                .andExpect(jsonPath("$.message.timezone", Is.is("newTimezone")));
    }

    @Test
    public void whenUpdateThenNotFound() throws Exception {
        mockMvc.perform(put("/cities/123123123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(
                                cityDto.setName("newName")
                                        .setLatitude(BigDecimal.valueOf(0.0))
                                        .setLongitude(BigDecimal.valueOf(0.0))
                                        .setTimezone("newTimezone")
                        )))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success", Is.is(false)))
                .andExpect(jsonPath("$.code", Is.is("NOT_FOUND")))
                .andExpect(jsonPath("$.error", Is.is("City with id: 123123123 not found")))
                .andExpect(jsonPath("$.message.name").doesNotExist());
    }

    @Test
    public void whenDeleteThenNotFound() throws Exception {
        mockMvc.perform(delete("/cities/123123123"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success", Is.is(false)))
                .andExpect(jsonPath("$.code", Is.is("NOT_FOUND")))
                .andExpect(jsonPath("$.error", Is.is("City with id: 123123123 not found")))
                .andExpect(jsonPath("$.message.name").doesNotExist());
    }

    @Test
    public void whenDeleteThenOk() throws Exception {
        mockMvc.perform(delete("/cities/4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", Is.is(true)))
                .andExpect(jsonPath("$.code", Is.is("OK")))
                .andExpect(jsonPath("$.message.name", Is.is("Tokio")))
                .andExpect(jsonPath("$.message.latitude").value("-11.0833"))
                .andExpect(jsonPath("$.message.longitude").value("-69.2667"))
                .andExpect(jsonPath("$.message.timezone", Is.is("America/La_Paz")));
    }
}
