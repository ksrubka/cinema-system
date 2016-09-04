package pl.com.bottega.cinemasystem.api;

import java.util.Collection;

public class ListAllCinemasResponse {

    private Collection<CinemaDto> cinemas;

    public ListAllCinemasResponse(Collection<CinemaDto> cinemas) {
        this.cinemas = cinemas;
    }
}
