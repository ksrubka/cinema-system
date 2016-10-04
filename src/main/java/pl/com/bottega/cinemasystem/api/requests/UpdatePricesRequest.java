package pl.com.bottega.cinemasystem.api.requests;

import pl.com.bottega.cinemasystem.api.exceptions.InvalidRequestException;
import pl.com.bottega.cinemasystem.api.utils.ValidationUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UpdatePricesRequest {

    private Long movieId;

    private Map<String, BigDecimal> prices;

    public UpdatePricesRequest() {
    }

    public UpdatePricesRequest(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }

    public void validateMovieId() {
        ValidationUtils.validateId(movieId, "Movie id is incorrect: " + movieId);
    }

    public void validate(Integer minAge) {
        validatePrices();
        Set<String> ticketKinds = prepareTicketKindsForValidation();
        validatePricesKinds(ticketKinds, minAge);
    }

    private void validatePrices() {
        if (prices == null) {
            throw new InvalidRequestException("Prices map is null");
        }
    }

    private Set<String> prepareTicketKindsForValidation() {
        Set<String> ticketKinds = new HashSet<>();
        prices.forEach((kind, price) -> ticketKinds.add(kind));
        return ticketKinds;
    }

    private void validatePricesKinds(Set<String> ticketKinds, Integer minAge) {
        validateIfNotEmpty();
        ValidationUtils.validateTicketKinds(ticketKinds, minAge);
    }

    private void validateIfNotEmpty() {
        for (String priceKind : prices.keySet()) {
            ValidationUtils.validateString(priceKind, "Ticket kind is empty");
        }
    }

    public Map<String, BigDecimal> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, BigDecimal> prices) {
        this.prices = prices;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
