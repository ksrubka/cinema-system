package pl.com.bottega.cinemasystem.api.responses;


import pl.com.bottega.cinemasystem.domain.Calculation;

public class CalculatePriceResponse {

    private Calculation calculation;

    public CalculatePriceResponse() {
    }

    public CalculatePriceResponse(Calculation calculation) {
        this.calculation = calculation;
    }

    public Calculation getCalculation() {
        return calculation;
    }

    public void setCalculation(Calculation calculation) {
        this.calculation = calculation;
    }
}
