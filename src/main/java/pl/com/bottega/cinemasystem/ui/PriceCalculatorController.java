package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemasystem.api.requests.CalculatePriceRequest;
import pl.com.bottega.cinemasystem.api.responses.CalculatePriceResponse;
import pl.com.bottega.cinemasystem.api.services.PriceCalculator;

@RestController
@RequestMapping("/price_calculator")
public class PriceCalculatorController {

    private PriceCalculator priceCalculator;

    public PriceCalculatorController(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @PostMapping
    public CalculatePriceResponse calculatePrice(@RequestBody CalculatePriceRequest request) {
        return priceCalculator.calculatePrice(request);
    }
}
