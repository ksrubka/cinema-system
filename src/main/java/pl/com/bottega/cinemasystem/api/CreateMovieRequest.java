package pl.com.bottega.cinemasystem.api;

import java.util.List;

public class CreateMovieRequest {

    private MovieDto movie;


    public void validate() {
        validateState();
    }

    private void validateState() {
        validateTitle();
        validateDescription();
        validateMinAge();
        validateActors();
        validateGenres();
        validateLength();
        validateId();
    }

    private void validateTitle() {
        if (movie.getTitle() == null || movie.getTitle().trim().isEmpty())
            throw new InvalidRequestException("movie title is required");
    }

    private void validateDescription() {
        if (movie.getDescription() == null || movie.getDescription().trim().isEmpty())
            throw new InvalidRequestException("movie description is required");
    }

    private void validateMinAge() {
        if (movie.getMinAge() == null)
            throw new InvalidRequestException("minimal age is required");
    }

    private void validateActors() {
        if (movie.getActors() == null || movie.getActors().isEmpty()) {
            throw new InvalidRequestException("actors list is required");
        }
        movie.getActors().forEach(actor -> {
            if(actor.trim().isEmpty() || actor == null){
                throw new InvalidRequestException("actor can not be null");
            }
        });
    }

    private void validateGenres() {
        if (movie.getGenres() == null || movie.getGenres().isEmpty()){
            throw new InvalidRequestException("genres list is required");
        }
        movie.getGenres().forEach(genre -> {
            if (genre.trim().isEmpty() || genre == null){
                throw new InvalidRequestException("genre can not be null or empty");
            }
        });
    }

    private void validateLength() {
        if (movie.getLength() == null || movie.getLength() <= 0)
            throw new InvalidRequestException("movie length is required");
    }

    private void validateId(){
        if(movie.getId() == null || movie.getId() == 0)
            throw new InvalidRequestException("movie id can not be null or zero");
    }


    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    public String getTitle() {
        return movie.getTitle();
    }

    public String getDescription() {
        return movie.getDescription();
    }

    public Integer getMinAge() {
        return movie.getMinAge();
    }

    public List<String> getActors() {
        return movie.getActors();
    }

    public List<String> getGenres() {
        return movie.getGenres();
    }

    public Integer getLength() {
        return movie.getLength();
    }
}
