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
import pl.com.bottega.cinemasystem.infrastructure.JPACinemaRepository;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.mock.staticmock.AnnotationDrivenStaticEntityMockingControl.verify;

/**
 * Created by Seta on 2016-09-10.
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateCinemaTest {


    @Mock
    private JPACinemaRepository cinemaRepository;
    @Mock
    private CreateCinemaRequest createCinemaRequest;
    @Mock
    private CinemaFactory cinemaFactory;

    @Mock
    private Cinema anyCinema;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private AdminPanel adminPanel;

    private String anyName = "anyName";
    private String anyCity = "anyCity";

    @Before
    public void setUp(){
        Cinema cinema = cinemaFactory.createCinema(createCinemaRequest);
    }


    @Test
    public void shouldCreateCinema() {

     when(cinemaFactory.createCinema(createCinemaRequest)).thenReturn(anyCinema);
     assertNotNull(anyCinema);

    }



    @Test
    public void shouldNotCreateCinemaIfNameNotExist() throws Exception {
        exception.expect(InvalidRequestException.class);


    }


}
