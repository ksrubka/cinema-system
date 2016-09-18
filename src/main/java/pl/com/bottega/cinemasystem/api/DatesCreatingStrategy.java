package pl.com.bottega.cinemasystem.api;

import java.time.LocalDateTime;
import java.util.Collection;

public interface DatesCreatingStrategy {

    Collection<LocalDateTime> generateShowDates();
}
