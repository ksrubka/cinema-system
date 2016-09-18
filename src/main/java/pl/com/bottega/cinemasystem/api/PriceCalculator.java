package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PriceCalculator {

    @Transactional
    public CalculatePriceResponse calculatePrice(CalculatePriceRequest request) {
        return null;
    }
}
