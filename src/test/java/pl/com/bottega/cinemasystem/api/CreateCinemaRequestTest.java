package pl.com.bottega.cinemasystem.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.Cinema;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateCinemaRequestTest {

    public static final String EMPTY_STRING = "  ";

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
        //given
        when(cinemaDto.getName()).thenReturn(anyName);
        when(cinemaDto.getCity()).thenReturn(anyCity);
        createCinemaRequest.setCinema(cinemaDto);
        //when
        createCinemaRequest.validate();
        //then
        assertEquals(cinemaDto.getName(), createCinemaRequest.getName());
        assertNotNull(createCinemaRequest.getCity());
        assertNotNull(createCinemaRequest.getName());
        assertEquals(cinemaDto.getCity(), createCinemaRequest.getCity());
        assertEquals(cinemaDto, createCinemaRequest.getCinema());


    }


    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateCinemaWithNameAsNull() {
        //given
        when(cinemaDto.getName()).thenReturn(null);
        when(cinemaDto.getCity()).thenReturn(anyCity);
        createCinemaRequest.setCinema(cinemaDto);
        //when
        createCinemaRequest.validate();
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateCinemaWithCityAsNull() {
        //given
        when(cinemaDto.getName()).thenReturn(anyName);
        when(cinemaDto.getCity()).thenReturn(null);
        createCinemaRequest.setCinema(cinemaDto);
        //when
        createCinemaRequest.validate();
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateCinemaWithEmptyCity() {
        //given
        when(cinemaDto.getName()).thenReturn(anyName);
        when(cinemaDto.getCity()).thenReturn(EMPTY_STRING);
        createCinemaRequest.setCinema(cinemaDto);
        //when
        createCinemaRequest.validate();
    }


}
