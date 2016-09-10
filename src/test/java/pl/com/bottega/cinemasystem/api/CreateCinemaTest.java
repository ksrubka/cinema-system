package pl.com.bottega.cinemasystem.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.Cinema;
import pl.com.bottega.cinemasystem.domain.CinemaRepository;
import pl.com.bottega.cinemasystem.domain.MovieRepository;
import pl.com.bottega.cinemasystem.domain.ShowsRepository;
import pl.com.bottega.cinemasystem.infrastructure.JPACinemaRepository;

import javax.persistence.ManyToOne;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateCinemaTest {

    @Mock
    private JPACinemaRepository cinemaRepository;
    @Mock
    private CreateCinemaRequest createCinemaRequest;
    @Mock
    private CinemaFactory cinemaFactory;
    @Mock
    private MovieFactory movieFactory;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private ShowsRepository showRepository;

    @Mock
    private Cinema anyCinema;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private AdminPanel adminPanel;

    private String anyName = "anyName";
    private String anyCity = "anyCity";

    @Before
    public void setUp(){
        adminPanel = new AdminPanel(cinemaRepository, movieRepository,
                showRepository, movieFactory, cinemaFactory);
    }

    @Test
    public void shouldCreateCinema() {

        //given
        when(cinemaFactory.createCinema(createCinemaRequest)).thenReturn(anyCinema);

        //when
        adminPanel.createCinema(createCinemaRequest);

        //then
        verify(cinemaRepository).save(anyCinema);
    }

    @Test
    public void shouldLoadCinema(){
        when(cinemaFactory.createCinema(createCinemaRequest)).thenReturn(anyCinema);
        cinemaRepository.load(anyCinema.getId());
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowInvalidExceptionWhenCinemaExist(){
        doThrow(InvalidRequestException.class).when(cinemaRepository).save(anyCinema);
        //when
        when(cinemaFactory.createCinema(createCinemaRequest)).thenReturn(anyCinema);
        adminPanel.createCinema(createCinemaRequest);
        //then
        adminPanel.createCinema(createCinemaRequest);
    }
}
