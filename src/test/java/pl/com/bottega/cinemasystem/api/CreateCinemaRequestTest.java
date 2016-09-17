package pl.com.bottega.cinemasystem.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.Cinema;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateCinemaRequestTest {

    public static final String EMPTY_STRING = "";

    private CreateCinemaRequest createCinemaRequest;

    @Mock
    private Cinema anyCinema;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private String anyName = "any name";
    private String anyCity = "any city";

    @Mock
    private CinemaDto cinemaDto;
    @Mock
    private CinemaFactory factory;

    @Before
    public void setUp() {
        createCinemaRequest = new CreateCinemaRequest();
    }

    @Test
    public void shouldValidateCinemaWithCorrectCinemaDto() {
        when(cinemaDto.getName()).thenReturn(anyName);
        when(cinemaDto.getCity()).thenReturn(anyCity);
        createCinemaRequest.setCinema(cinemaDto);
        createCinemaRequest.validate();
    }

    @Test
    public void shouldNotValidateCinemaWithEmptyNameAndCity() {}
}
