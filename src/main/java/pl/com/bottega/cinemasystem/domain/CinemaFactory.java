package pl.com.bottega.cinemasystem.domain;

import org.springframework.stereotype.Component;
import pl.com.bottega.cinemasystem.api.CreateCinemaRequest;

@Component
public class CinemaFactory {

    public Cinema createCinema(CreateCinemaRequest createCinemaRequest) {
        return new Cinema(createCinemaRequest.getName(), createCinemaRequest.getCity());
    }
}
