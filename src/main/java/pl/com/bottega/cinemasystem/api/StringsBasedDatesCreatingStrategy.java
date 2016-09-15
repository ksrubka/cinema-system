package pl.com.bottega.cinemasystem.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


public class StringsBasedDatesCreatingStrategy implements DatesCreatingStrategy {

    private Collection<String> stringDates;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm");

    public StringsBasedDatesCreatingStrategy(Collection<String> dates) {
        this.stringDates = dates;
    }

    @Override
    public void validate() {
        checkIfDatesAreSpecified();
        checkIfDatesAreCorrect();
        checkIfDateHasNotPassed();
        checkIfDatesAreNotRedundant();
    }

    private void checkIfDatesAreSpecified() {
        if (stringDates == null) {
            throw new InvalidRequestException("Dates not specified");
        }
    }

    private void checkIfDatesAreCorrect() {
        stringDates.forEach(date -> {
            try {
                formatter.parse(date);
            } catch (ParseException e) {
                throw new InvalidRequestException("Incorrect date format");
            }
        });
    }

    private void checkIfDateHasNotPassed() {
        //// TODO: 09.09.2016
    }


    private void checkIfDatesAreNotRedundant() {
        //// TODO: 09.09.2016
    }

    @Override
    public List<Date> generateShowDates() {
        List<Date> dates = new ArrayList<>();
        stringDates.forEach(date -> {
            try {
                dates.add(formatter.parse(date));
            } catch (ParseException e) {
                throw new InvalidRequestException("Incorrect date format");
            }
        });
        return dates;
    }
}
