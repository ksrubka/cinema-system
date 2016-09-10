package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Service;
import pl.com.bottega.cinemasystem.domain.Cinema;

/**
 * Created by Seta on 2016-09-10.
 */
@Service
public class CinemaFactory {
    public Cinema createCinema(CreateCinemaRequest createCinemaRequest) {
        return new Cinema(createCinemaRequest.getName(), createCinemaRequest.getCity());
    }
}
