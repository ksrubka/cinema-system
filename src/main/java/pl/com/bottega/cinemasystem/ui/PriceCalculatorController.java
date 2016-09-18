package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemasystem.api.CalculatePriceRequest;
import pl.com.bottega.cinemasystem.api.CalculatePriceResponse;

//@RestController
@RequestMapping("/price_calculator")
public class PriceCalculatorController {

    private CalculatePriceRequest calculatePriceRequest;
    private CalculatePriceResponse calculatePriceResponse;

    public PriceCalculatorController(CalculatePriceRequest calculatePriceRequest,
                                     CalculatePriceResponse calculatePriceResponse){
        this.calculatePriceRequest = calculatePriceRequest;
        this.calculatePriceResponse = calculatePriceResponse;
    }
    @PostMapping
    public CalculatePriceResponse calculatePrice(@RequestBody CalculatePriceRequest request){
        return null;
    }
}
