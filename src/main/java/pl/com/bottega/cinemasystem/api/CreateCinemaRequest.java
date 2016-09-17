package pl.com.bottega.cinemasystem.api;

public class CreateCinemaRequest {

    private CinemaDto cinema;

    public void validate() {
        validateCity();
        validateName();
        validateId();
    }

    private void validateName() {
        if (cinema.getName() == null || cinema.getName().trim().isEmpty())
            throw new InvalidRequestException("cinema name is required");
    }

    private void validateCity() {
        if (cinema.getCity() == null || cinema.getCity().trim().isEmpty())
            throw new InvalidRequestException("cinema city location is required");
    }
    private void validateId(){
        if(cinema.getId() == null || cinema.getId() == 0)
            throw new InvalidRequestException("cinema id can not be null or zero");
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
