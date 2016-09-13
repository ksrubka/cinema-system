package pl.com.bottega.cinemasystem.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.Cinema;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class CinemaFactoryTest {

    private CinemaFactory cinemaFactory;
    @Mock
    private CreateCinemaRequest createCinemaRequest;

    @Test
    public void shouldCreateCinema() {
        cinemaFactory = new CinemaFactory();
        Cinema cinema = cinemaFactory.createCinema(createCinemaRequest);
        assertNotNull(cinema);
    }
}










