package pl.com.bottega.cinemasystem.api;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.*;

import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;


@RunWith(MockitoJUnitRunner.class)
public class ShowFactoryTest {

    @Mock
    private Cinema cinema;

    @Mock
    private Movie movie;

    @Mock
    private CreateShowsRequest createShowsRequest;

    private ShowsFactory showsFactory;

    @Before
    public void setUp() {
        showsFactory = new ShowsFactory();
    }

    @Test
    public void shouldGetShow() {
        Collection<Show> show = showsFactory.getShows(cinema, movie, createShowsRequest);
        assertNotNull(show);
    }
}
