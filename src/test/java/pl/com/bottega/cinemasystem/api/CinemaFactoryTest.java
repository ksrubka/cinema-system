package pl.com.bottega.cinemasystem.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.AssertionErrors;
import pl.com.bottega.cinemasystem.domain.Cinema;
import pl.com.bottega.cinemasystem.domain.CinemaRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

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










