package pl.com.bottega.cinemasystem.api;

import java.util.Date;
import java.util.List;

public interface DatesCreatingStrategy {

    void validate();
    List<Date> generateShowDates();
}
