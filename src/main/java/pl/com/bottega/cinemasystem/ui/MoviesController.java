package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemasystem.api.AdminPanel;
import pl.com.bottega.cinemasystem.api.CreateMovieRequest;
import pl.com.bottega.cinemasystem.domain.Movie;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private AdminPanel adminPanel;

    @PutMapping
    public void create(@RequestBody CreateMovieRequest request){
        adminPanel.createMovie(request);
    }


}
