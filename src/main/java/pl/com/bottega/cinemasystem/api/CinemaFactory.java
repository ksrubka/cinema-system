package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Service;
import pl.com.bottega.cinemasystem.domain.Cinema;

@Service
public class CinemaFactory {
    public Cinema createCinema(CreateCinemaRequest createCinemaRequest) {
        return new Cinema(createCinemaRequest.getName(), createCinemaRequest.getCity());
    }

    public CinemaFactory(){}
}
