package pl.com.bottega.cinemasystem.api;

import java.time.LocalDateTime;
import java.util.*;

public class StringsBasedDatesCreatingStrategy implements DatesCreatingStrategy {

    private Collection<LocalDateTime> dates;

    public StringsBasedDatesCreatingStrategy(Collection<LocalDateTime> dates) {
        this.dates = dates;
    }

    @Override
    public Collection<LocalDateTime> generateShowDates() {
        return new TreeSet<>(dates);
    }
}
