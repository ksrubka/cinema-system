package pl.com.bottega.cinemasystem.domain;

import pl.com.bottega.cinemasystem.api.CreateCinemaRequest;


public class CinemaFactory {
    public static Cinema createCinema(CreateCinemaRequest createCinemaRequest) {
        return new Cinema(createCinemaRequest.getName(), createCinemaRequest.getCity());
    }
}
