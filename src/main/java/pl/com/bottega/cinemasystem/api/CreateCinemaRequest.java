package pl.com.bottega.cinemasystem.api;

public class CreateCinemaRequest {

    private CinemaDto cinema;

    public void validate() {
        if (cinema == null)
            throw new InvalidRequestException("Cinema is required");
        cinema.validate();
        }

    public String getName() {
        return cinema.getName();
    }

    public String getCity() {
        return cinema.getCity();
    }

    public CinemaDto getCinema() {
        return cinema;
    }

    public void setCinema(CinemaDto cinema) {
        this.cinema = cinema;
    }
}
