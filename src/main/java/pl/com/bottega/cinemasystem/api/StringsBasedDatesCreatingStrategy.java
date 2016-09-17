package pl.com.bottega.cinemasystem.api;

import pl.com.bottega.cinemasystem.api.utils.DateUtil;

import java.util.*;

public class StringsBasedDatesCreatingStrategy implements DatesCreatingStrategy {

    private Collection<String> stringDates;

    public StringsBasedDatesCreatingStrategy(Collection<String> dates) {
        this.stringDates = dates;
    }

    @Override
    public Set<Date> generateShowDates() {
        Set<Date> dates = new TreeSet<>();
        stringDates.forEach(date ->
                dates.add(DateUtil.parseDate(date)));
        return dates;
    }
}
