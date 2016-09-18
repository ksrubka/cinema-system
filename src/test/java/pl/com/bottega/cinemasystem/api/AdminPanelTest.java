package pl.com.bottega.cinemasystem.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.*;

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
    private ShowsFactory anyShowsFactory;

    @Mock
    private CreateMovieRequest anyCreateMovieRequest;

    @Mock
    private CreateCinemaRequest anyCreateCinemaRequest;

    @Mock
    private Movie anyMovie;

    @Mock
    private Cinema anyCinema;

    private AdminPanel adminPanel;

    @Before
    public void setUp() {
        adminPanel = new AdminPanel(anyCinemaRepository, anyMovieRepository, anyShowsRepository, anyMovieFactory, anyCinemaFactory, anyShowsFactory);
    }

    @Test
    public void shouldCreateMovie() {
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
    public void shouldThrowInvalidRequestExWhenAddedMovieAlreadyExists() {
        //given
        doThrow(InvalidRequestException.class).when(anyMovieRepository).save(anyMovie);
        when(anyMovieFactory.createMovie(anyCreateMovieRequest)).thenReturn(anyMovie);
        adminPanel.createMovie(anyCreateMovieRequest);
        //when
        adminPanel.createMovie(anyCreateMovieRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowInvalidRequestExWhenAddedCinemaAlreadyExists() {
        //given
        doThrow(InvalidRequestException.class).when(anyCinemaRepository).save(anyCinema);
        when(anyCinemaFactory.createCinema(anyCreateCinemaRequest)).thenReturn(anyCinema);
        adminPanel.createCinema(anyCreateCinemaRequest);
        //when
        adminPanel.createCinema(anyCreateCinemaRequest);
    }

}