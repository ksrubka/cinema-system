package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemasystem.api.services.AdminPanel;
import pl.com.bottega.cinemasystem.api.requests.CreateMovieRequest;
import pl.com.bottega.cinemasystem.api.requests.UpdatePricesRequest;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private AdminPanel adminPanel;

    public MoviesController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @PutMapping
    public void create(@RequestBody CreateMovieRequest request) {
        adminPanel.createMovie(request);
    }

    @PutMapping("/{movieId}/prices")
    public void updatePrices(@PathVariable Long movieId, @RequestBody UpdatePricesRequest request) {
        request.setMovieId(movieId);
        adminPanel.updatePrices(request);
    }
}