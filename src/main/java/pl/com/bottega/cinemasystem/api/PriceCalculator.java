package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.domain.Show;
import pl.com.bottega.cinemasystem.domain.ShowsRepository;

public class PriceCalculator implements ShowsRepository {

    private Show show;

    @Override
    public void save(Show show) {

    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }


}
