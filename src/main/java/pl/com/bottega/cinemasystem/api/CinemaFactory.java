package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Component;
import pl.com.bottega.cinemasystem.domain.Cinema;

@Component
public class CinemaFactory {
    public Cinema createCinema(CreateCinemaRequest createCinemaRequest) {
        return new Cinema(createCinemaRequest.getName(), createCinemaRequest.getCity());
    }
}
