package pl.com.bottega.cinemasystem.domain;

import org.springframework.stereotype.Component;
import pl.com.bottega.cinemasystem.api.CreateCinemaRequest;
import pl.com.bottega.cinemasystem.domain.Cinema;

@Component
public class CinemaFactory {
    public static Cinema createCinema(CreateCinemaRequest createCinemaRequest) {
        return new Cinema(createCinemaRequest.getName(), createCinemaRequest.getCity());
    }
}
