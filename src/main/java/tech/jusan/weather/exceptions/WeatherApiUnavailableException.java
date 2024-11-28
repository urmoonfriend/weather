package tech.jusan.weather.exceptions;

public class WeatherApiUnavailableException extends Exception {
    public WeatherApiUnavailableException() {
        super("Weather API is currently unavailable. Please try again later.");
    }
}
