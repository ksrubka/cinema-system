package pl.com.bottega.cinemasystem.api;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.CinemaRepository;
import pl.com.bottega.cinemasystem.domain.MovieRepository;
import pl.com.bottega.cinemasystem.domain.Show;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;


@RunWith(MockitoJUnitRunner.class)
public class ShowFactoryTest {

    private ShowsFactory showsFactory;
    @Mock
    private CreateShowsRequest createShowsRequest;
    @Mock
    private CinemaRepository cinemaRepository;
    @Mock
    private MovieRepository movieRepository;
    private Long anyId = 1L;
    @Mock
    private ManyShowsDto showsDto;


    @Before
    public void setUp() {
        showsFactory = new ShowsFactory(cinemaRepository, movieRepository);
    }

    @Test
    public void shouldGetShow() {
        List<Show> show = showsFactory.getShows(anyId, createShowsRequest);
        assertNotNull(show);
    }
}
