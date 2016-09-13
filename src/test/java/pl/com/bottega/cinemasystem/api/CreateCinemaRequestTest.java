package pl.com.bottega.cinemasystem.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.Cinema;

@RunWith(MockitoJUnitRunner.class)
public class CreateCinemaRequestTest {

    private CreateCinemaRequest createCinemaRequest;
    @Mock
    private Cinema anyCinema;
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private String anyName = "anyName";
    private String anyCity = "anyCity";
    private String emptyString = "";

    @Before
    public void setUp() {
        createCinemaRequest = new CreateCinemaRequest();
    }

    @Test
    public void shouldValidateCinema() {

    }
}
