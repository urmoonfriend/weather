package tech.jusan.weather.exceptions;

import static java.lang.String.format;

public class CityNotFoundException extends Exception {
    public CityNotFoundException(Long id) {
        super(format("City with id: %d not found", id));
    }
}
