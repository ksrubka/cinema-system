package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.CinemaRepository;

public class CreateCinemaRequest {

    private CinemaDto cinema;

    public void validate(CinemaRepository cinemaRepository) {
        if (cinema == null) {
            throw new InvalidRequestException("Cinema is required");
        }
        cinema.validate(cinemaRepository);
    }

    public CinemaDto getCinema() {
        return cinema;
    }

    public void setCinema(CinemaDto cinema) {
        this.cinema = cinema;
    }

    public String getName() {
        return cinema.getName();
    }

    public String getCity() {
        return cinema.getCity();
    }
}
