package pl.com.bottega.cinemasystem.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.Cinema;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class CinemaFactoryTest {

    @Mock
    private CreateCinemaRequest createCinemaRequest;

    private CinemaFactory cinemaFactory;

    @Before
    public void setUp() {
        cinemaFactory = new CinemaFactory();
    }

    @Test
    public void shouldCreateCinema() {
        Cinema cinema = cinemaFactory.createCinema(createCinemaRequest);
        assertNotNull(cinema);
    }
}










