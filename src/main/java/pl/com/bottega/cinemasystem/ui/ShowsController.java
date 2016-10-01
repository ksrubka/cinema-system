package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.*;
import pl.com.bottega.cinemasystem.api.AdminPanel;
import pl.com.bottega.cinemasystem.api.CreateShowsRequest;
import pl.com.bottega.cinemasystem.api.CustomerPanel;
import pl.com.bottega.cinemasystem.api.GetShowSeatsResponse;

@RestController
@RequestMapping
public class ShowsController {

    private AdminPanel adminPanel;
    private CustomerPanel customerPanel;

    public ShowsController(AdminPanel adminPanel, CustomerPanel customerPanel) {
        this.customerPanel = customerPanel;
        this.adminPanel = adminPanel;
    }

    @PutMapping("/cinemas/{cinemaId}/shows")
    public void create(@PathVariable Long cinemaId, @RequestBody CreateShowsRequest request) {
        request.setCinemaId(cinemaId);
        adminPanel.createShows(request);
    }

    @GetMapping("/shows/{showId}/seats")
    public GetShowSeatsResponse getShowSeats(@PathVariable Long showId){
       return customerPanel.getShowSeats(showId);

    }
}
