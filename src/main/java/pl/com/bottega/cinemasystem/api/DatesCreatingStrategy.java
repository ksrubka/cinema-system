package pl.com.bottega.cinemasystem.api;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface DatesCreatingStrategy {

    void validate();
    Set<Date> generateShowDates();
}
