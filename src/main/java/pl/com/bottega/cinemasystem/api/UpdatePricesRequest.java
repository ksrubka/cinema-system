package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.api.utils.ValidationUtils;
import pl.com.bottega.cinemasystem.domain.Movie;

import java.math.BigDecimal;
import java.util.Map;

public class UpdatePricesRequest {

    private Long movieId;
    private int minAge;

    private Map<String, BigDecimal> prices;

    public UpdatePricesRequest() {
    }

    public UpdatePricesRequest(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }

    public void validateMovieId() {
        ValidationUtils.validateId(movieId, "Movie id is incorrect: " + movieId);
    }

    public void validate(Movie movie) {
        setMinAge(movie.getMinAge());
        validatePrices();
        validatePricesKinds();
    }

    private void validatePrices() {
        if (prices == null) {
            throw new InvalidRequestException("Prices map is null");
        }
    }

    private void validatePricesKinds() {
        validateIfNotEmpty();
        validateAvailability();
    }

    private void validateIfNotEmpty() {
        for (String priceKind : prices.keySet()) {
            ValidationUtils.validateString(priceKind, "Ticket kind is empty");
        }
    }

    private void validateAvailability() {
        if (filmIsAvailableForEveryone()) {
            requestShouldContainAllTicketTypes();
        }
        if (minAgeIs16()) {
            requestShouldContainShoolStudentAndRegularTicketTypes();
        }
        if (minAgeIs18()) {
            requestShouldContainStudentAndRegularTicketTypes();
        }
    }

    private boolean filmIsAvailableForEveryone() {
        return minAge == 0;
    }

    private boolean minAgeIs16() {
        return minAge >= 16;
    }

    private boolean minAgeIs18() {
        return minAge == 18;
    }

    private void requestShouldContainAllTicketTypes() {
        if (!(prices.containsKey("children") &&
                prices.containsKey("school") &&
                prices.containsKey("student") &&
                prices.containsKey("regular"))) {
            throw new InvalidRequestException("More ticket kinds required to declare");
        }
    }

    private void requestShouldContainShoolStudentAndRegularTicketTypes() {
        if (!(prices.containsKey("school") &&
                prices.containsKey("student") &&
                prices.containsKey("regular"))) {
            throw new InvalidRequestException("More ticket kinds required to declare");
        }
        if (prices.containsKey("children")) {
            throw new InvalidRequestException("Children ticket kind is not required " +
                    "when minimum age for movie is: " + minAge);
        }
    }

    private void requestShouldContainStudentAndRegularTicketTypes() {
        if (!(prices.containsKey("student") &&
                prices.containsKey("regular"))) {
            throw new InvalidRequestException("More price types required to declare");
        }
        if (prices.containsKey("children") || prices.containsKey("school")) {
            throw new InvalidRequestException("Incorrect price types were declared");
        }
    }

    public Map<String, BigDecimal> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
