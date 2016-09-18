package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemasystem.api.AdminPanel;
import pl.com.bottega.cinemasystem.api.CalculatePriceRequest;
import pl.com.bottega.cinemasystem.api.CalculatePriceResponse;

@RestController
@RequestMapping("/price_calculator")
public class PriceCalculatorController {


    private AdminPanel adminPanel;

    public PriceCalculatorController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @PostMapping
    public CalculatePriceResponse calculatePrice(@RequestBody CalculatePriceRequest request) {
        return adminPanel.calculatePrice(request);
    }
}
