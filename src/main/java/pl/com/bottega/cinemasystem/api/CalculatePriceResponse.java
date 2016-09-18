package pl.com.bottega.cinemasystem.api;


public class CalculatePriceResponse {
    private PriceCalculator priceCalculator;

    public PriceCalculator getPriceCalculator() {
        return priceCalculator;
    }

    public void setPriceCalculator(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }
}
