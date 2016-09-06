package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemasystem.api.AdminPanel;
import pl.com.bottega.cinemasystem.api.CreateShowsRequest;

@RestController
@RequestMapping("/shows")
public class ShowsController {

    private AdminPanel adminPanel;

    public ShowsController(AdminPanel adminPanel) {
        this.adminPanel = adminPanel;
    }

    @PutMapping
    public void create(@RequestBody CreateShowsRequest request) {
        adminPanel.createShows(request);
    }
}
