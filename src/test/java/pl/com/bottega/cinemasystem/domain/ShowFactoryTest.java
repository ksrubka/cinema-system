package pl.com.bottega.cinemasystem.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.api.requests.CreateShowsRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowFactoryTest {

    @Mock
    private Cinema cinema;

    @Mock
    private Movie movie;

    @Mock
    private CreateShowsRequest createShowsRequest;

    private Collection<LocalDateTime> dates = new ArrayList<>();
    private ShowsFactory showsFactory;

    @Before
    public void setUp() {
        showsFactory = new ShowsFactory();
        dates.add(LocalDateTime.now());
        dates.add(LocalDateTime.now());
        dates.add(LocalDateTime.now());
    }

    @Test
    public void shouldGetShow() {
        when(createShowsRequest.getShowDates()).thenReturn(dates);
        Collection<Show> show = showsFactory.createShows(cinema, movie, createShowsRequest.getShowDates());
        assertNotNull(show);
    }
}
