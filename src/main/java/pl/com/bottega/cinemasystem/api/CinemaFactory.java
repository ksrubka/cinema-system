package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.Cinema;

/**
 * Created by Seta on 2016-09-10.
 */
public class CinemaFactory {
    public Cinema createCinema(CreateCinemaRequest createCinemaRequest) {
        return new Cinema(createCinemaRequest.getName(), createCinemaRequest.getCity());
    }
}
