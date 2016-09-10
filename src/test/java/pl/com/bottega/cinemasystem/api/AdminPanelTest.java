package pl.com.bottega.cinemasystem.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.*;

import javax.persistence.ManyToOne;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminPanelTest {

    @Mock
    private CinemaRepository anyCinemaRepository;

    @Mock
    private MovieRepository anyMovieRepository;

    @Mock
    private ShowsRepository anyShowsRepository;

    @Mock
    private MovieFactory anyMovieFactory;

    @Mock
    private CinemaFactory anyCinemaFactory;

    @Mock
    private CreateMovieRequest anyCreateMovieRequest;

    @Mock
    private CreateCinemaRequest anyCreateCinemaRequest;

    @Mock
    private Movie anyMovie;

    @Mock
    private Cinema anyCinema;

    private AdminPanel adminPanel;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private MovieFactory movieFactory;



    @Before
    public void setUp() {
        adminPanel = new AdminPanel(anyCinemaRepository,movieRepository,anyShowsRepository,anyCinemaFactory);
    }

    @Test
    public void shouldCreateNewMovie() {
        //given
        when(anyMovieFactory.createMovie(anyCreateMovieRequest)).thenReturn(anyMovie);

        //when
        adminPanel.createMovie(anyCreateMovieRequest);

        //then
        verify(anyMovieRepository).save(anyMovie);
    }

    @Test
    public void shouldCreateCinema() {

        //given
        when(anyCinemaFactory.createCinema(anyCreateCinemaRequest)).thenReturn(anyCinema);

        //when
        adminPanel.createCinema(anyCreateCinemaRequest);

        //then
        verify(anyCinemaRepository).save(anyCinema);
    }


    @Test(expected = InvalidRequestException.class)
    public void shouldThrownInvalidRequestExWhenAddedCinemaAlreadyExists(){
        //given
        doThrow(InvalidRequestException.class).when(anyMovieRepository).save(anyMovie);

        when(anyMovieFactory.createMovie(anyCreateMovieRequest)).thenReturn(anyMovie);
        adminPanel.createMovie(anyCreateMovieRequest);

        //when
        adminPanel.createMovie(anyCreateMovieRequest);
    }


    @Test(expected = InvalidRequestException.class)
    public void shouldThrowInvalidExceptionWhenCinemaExist(){
        doThrow(InvalidRequestException.class).when(anyCinemaRepository).save(anyCinema);
        //when
        when(anyCinemaFactory.createCinema(anyCreateCinemaRequest)).thenReturn(anyCinema);
        adminPanel.createCinema(anyCreateCinemaRequest);
        //then
        adminPanel.createCinema(anyCreateCinemaRequest);

    }
}