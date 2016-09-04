package pl.com.bottega.cinemasystem.api;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException() {

        super("Invalid request exception");
    }
}
