package pl.com.bottega.cinemasystem.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.Cinema;

import javax.persistence.criteria.CriteriaBuilder;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateCinemaRequestTest {

    private CreateCinemaRequest createCinemaRequest;
    @Mock
    private Cinema anyCinema;
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private String anyName = "anyName";
    private String anyCity = "anyCity";
    @Mock
    private CinemaDto cinemaDto;
    @Mock
    private CinemaFactory factory;


    @Before
    public void setUp() {
        createCinemaRequest = new CreateCinemaRequest();
    }

    @Test
    public void shouldValidateCinema() {
        when(cinemaDto.getName()).thenReturn(anyName);
        when(cinemaDto.getCity()).thenReturn(anyCity);
        createCinemaRequest.setCinema(cinemaDto);
        createCinemaRequest.validate();

    }



}
