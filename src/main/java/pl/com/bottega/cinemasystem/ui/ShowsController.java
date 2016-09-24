package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemasystem.api.AdminPanel;
import pl.com.bottega.cinemasystem.api.CreateShowsRequest;

@RestController
@RequestMapping("/cinemas/{cinemaId}/shows")
public class ShowsController {

    private AdminPanel adminPanel;

    public ShowsController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @PutMapping
    public void create(@PathVariable Long cinemaId, @RequestBody CreateShowsRequest request) {
        request.setCinemaId(cinemaId);
        adminPanel.createShows(request);
    }
}
