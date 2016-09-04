package pl.com.bottega.cinemasystem.ui;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pl.com.bottega.cinemasystem.api.InvalidRequestException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<String> handleAuthRequiredException(MultipartHttpServletRequest req, InvalidRequestException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json"); //TODO
        String msg = "{'error': '%s'}";
        return new ResponseEntity<String>(
                String.format(msg, ex.getMessage()),
                headers,
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

}
