package pl.com.bottega.cinemasystem.api;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String msg) {
        super(msg);
    }
}
