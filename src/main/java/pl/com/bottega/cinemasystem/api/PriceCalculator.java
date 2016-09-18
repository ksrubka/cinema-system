package pl.com.bottega.cinemasystem.api;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.domain.Show;
import pl.com.bottega.cinemasystem.domain.ShowsRepository;

@Service
public class PriceCalculator {

    private ShowsRepository showsRepository;
    private Show show;

    @Transactional
    public CalculatePriceResponse calculatePrice(CalculatePriceRequest request) {
        return null;
    }
}
