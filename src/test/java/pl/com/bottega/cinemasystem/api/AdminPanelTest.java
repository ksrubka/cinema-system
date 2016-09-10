package pl.com.bottega.cinemasystem.api;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.CinemaRepository;
import pl.com.bottega.cinemasystem.domain.Movie;
import pl.com.bottega.cinemasystem.domain.MovieRepository;
import pl.com.bottega.cinemasystem.domain.ShowsRepository;

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
    private CreateMovieRequest anyCreateMovieRequest;

    @Mock
    private Movie anyMovie;

    private AdminPanel adminPanel;


    @Before
    public void setUp() {
        adminPanel = new AdminPanel(anyCinemaRepository, anyMovieRepository, anyShowsRepository, anyMovieFactory);
    }


    @Test
    public void shouldCreateNewCinema() {
        //given
        when(anyMovieFactory.createMovie(anyCreateMovieRequest)).thenReturn(anyMovie);

        //when
        adminPanel.createMovie(anyCreateMovieRequest);

        //then
        verify(anyMovieRepository).save(anyMovie);
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


}