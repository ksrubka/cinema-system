package pl.com.bottega.cinemasystem.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateCinemaRequestTest {

    @Mock
    private CinemaDto cinemaDto;

    @Mock
    private CinemaFactory factory;

    private CreateCinemaRequest createCinemaRequest;

    private String emptyString = " ";
    private String anyName = "any name";
    private String anyCity = "any city";

    @Before
    public void setUp() {
        createCinemaRequest = new CreateCinemaRequest();
    }

    @Test
    public void shouldValidateCinemaWithCorrectCinemaDto() {
        //given
        createCinema();
        //when
        createCinemaRequest.validate();
        //then
        assertCinemaTest();
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateCinemaWithNameAsNull() {
        //given
        createCinemaRequestInstance(null, anyCity);

        //when
        createCinemaRequest.validate();
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateCinemaWithCityAsNull() {
        //given
        createCinemaRequestInstance(anyName, null);

        //when
        createCinemaRequest.validate();
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateCinemaWithEmptyName() {
        //given
        createCinemaRequestInstance(emptyString, anyCity);

        //when
        createCinemaRequest.validate();
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateCinemaWithEmptyCity() {
        //given
        createCinemaRequestInstance(anyName, emptyString);

        //when
        createCinemaRequest.validate();
    }

    private void createCinema() {
        createCinemaRequestInstance(anyName, anyCity);
    }

    private void assertCinemaTest() {
        assertEquals(cinemaDto.getName(), createCinemaRequest.getName());
        assertEquals(cinemaDto.getCity(), createCinemaRequest.getCity());
        assertEquals(cinemaDto, createCinemaRequest.getCinema());
    }

    private void createCinemaRequestInstance(String name, String city) {
        cinemaDto = new CinemaDto();
        createCinemaRequest.setCinema(cinemaDto);
        cinemaDto.setName(name);
        cinemaDto.setCity(city);

    }
}
