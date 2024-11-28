package tech.jusan.weather.exceptions;

public class AuthException extends Exception {
    public AuthException() {
        super("Authentication failed. Please check the API key.");
    }
}
