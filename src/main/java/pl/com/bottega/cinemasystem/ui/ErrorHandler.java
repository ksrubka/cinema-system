package pl.com.bottega.cinemasystem.ui;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.com.bottega.cinemasystem.api.InvalidRequestException;

@ControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<String> handleAuthRequiredException() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<String>(
                "{'error': 'Invalid Request Exception'}",
                headers,
                HttpStatus.UNAUTHORIZED
        );
    }
}
