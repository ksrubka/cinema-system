package pl.com.bottega.cinemasystem.api;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.api.dtos.CalendarDto;
import pl.com.bottega.cinemasystem.api.dtos.ManyShowsDto;
import pl.com.bottega.cinemasystem.api.dtos.MovieDto;
import pl.com.bottega.cinemasystem.api.exceptions.InvalidRequestException;
import pl.com.bottega.cinemasystem.api.requests.CreateCinemaRequest;
import pl.com.bottega.cinemasystem.api.requests.CreateMovieRequest;
import pl.com.bottega.cinemasystem.api.requests.CreateShowsRequest;
import pl.com.bottega.cinemasystem.api.requests.UpdatePricesRequest;
import pl.com.bottega.cinemasystem.api.services.AdminPanel;
import pl.com.bottega.cinemasystem.domain.*;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminPanelTest {

    private Long anyCinemaId = 1L;
    private Long anyMovieId = 1L;
    private Long anyShowId = 1L;
    private String anyMovieTitle = "any movie title";
    private String anyMovieDescription = "any movie description";
    private Integer anyMovieMinAge = 0;
    private Integer anyMovieLength = 120;
    private Set<String> anyMovieActors = new HashSet<>();
    private Set<String> anyMovieGenres = new HashSet<>();

    @Mock
    private CinemaRepository anyCinemaRepository;

    @Mock
    private MovieRepository anyMovieRepository;

    @Mock
    private ShowsRepository anyShowsRepository;

    @Mock
    private ShowsFactory anyShowsFactory;

    @Mock
    private TicketPricesFactory anyTicketPricesFactory;

    @Mock
    private CreateMovieRequest anyCreateMovieRequest;

    @Mock
    private CreateCinemaRequest anyCreateCinemaRequest;

    private CreateShowsRequest anyCreateShowsRequest;

    @Mock
    private Movie anyMovie;

    @Mock
    private Cinema anyCinema;

    @Mock
    private Show anyShow;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private AdminPanel adminPanel;

    @Before
    public void setUp() {
        adminPanel = new AdminPanel(anyCinemaRepository, anyMovieRepository, anyShowsRepository,
                anyShowsFactory, anyTicketPricesFactory);
    }

   /* @Test
    public void shouldCreateMovie() {
        //given
        when(anyMovieFactory.createMovie(anyCreateMovieRequest)).thenReturn(anyMovie);
        //when
        adminPanel.createMovie(anyCreateMovieRequest);
        //then
        verify(anyMovieRepository).save(anyMovie);
    }*/

    /*@Test
    public void shouldCreateCinema() {
        //given
        when(anyCinemaFactory.createCinema(anyCreateCinemaRequest)).thenReturn(anyCinema);
        //when
        adminPanel.createCinema(anyCreateCinemaRequest);
        //then
        verify(anyCinemaRepository).save(anyCinema);
    }*/

  /*  @Test(expected = InvalidRequestException.class)
    public void shouldThrowInvalidRequestExWhenAddedMovieAlreadyExists() {
        //given
        doThrow(InvalidRequestException.class).when(anyMovieRepository).save(anyMovie);
        when(anyMovieFactory.createMovie(anyCreateMovieRequest)).thenReturn(anyMovie);
        adminPanel.createMovie(anyCreateMovieRequest);
        //when
        adminPanel.createMovie(anyCreateMovieRequest);
    }*/

  /*  @Test(expected = InvalidRequestException.class)
    public void shouldThrowInvalidRequestExWhenAddedCinemaAlreadyExists() {
        //given
        doThrow(InvalidRequestException.class).when(anyCinemaRepository).save(anyCinema);
        when(anyCinemaFactory.createCinema(anyCreateCinemaRequest)).thenReturn(anyCinema);
        adminPanel.createCinema(anyCreateCinemaRequest);
        //when
        adminPanel.createCinema(anyCreateCinemaRequest);
    }*/

   /* @Test
    public void shouldCreateShowsWithDates() {
        prepareCreateShowsRequestInstanceWithDates();
        when(anyCinemaRepository.load(anyCinemaId)).thenReturn(anyCinema);
        when(anyMovieRepository.load(anyMovieId)).thenReturn(anyMovie);

        adminPanel.createShows(anyCreateShowsRequest);

        verify(anyShowsFactory).createShows(anyCinema, anyMovie, anyCreateShowsRequest);
    }*/

  /*  @Test
    public void shouldCreateShowsWithCalendar() {
        prepareCreateShowsRequestInstanceWithCalendar();
        when(anyCinemaRepository.load(anyCinemaId)).thenReturn(anyCinema);
        when(anyMovieRepository.load(anyMovieId)).thenReturn(anyMovie);

        adminPanel.createShows(anyCreateShowsRequest);

        verify(anyShowsFactory).createShows(anyCinema, anyMovie, anyCreateShowsRequest);
    }*/

    @Test
    public void shouldUpdatePrices() {
        //given
        when(anyMovieRepository.load(anyMovieId)).thenReturn(anyMovie);
        //when
        UpdatePricesRequest updatePricesRequest = anyUpdatePricesRequest();
        //then
        adminPanel.updatePrices(updatePricesRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotUpdatePricesBecauseMovieDoesNotExists() {
        //given
        when(anyMovieRepository.load(anyMovieId)).thenReturn(anyMovie);
        //when
        UpdatePricesRequest updatePricesRequest = anyUpdatePricesRequest();
        updatePricesRequest.setMovieId(8343453L);
        //then
        adminPanel.updatePrices(updatePricesRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotUpdatePricesBecauseTicketListIsIncompleted() {
        //given
        when(anyMovieRepository.load(anyMovieId)).thenReturn(anyMovie);
        //when
        UpdatePricesRequest updatePricesRequest = anyUpdatePricesRequestIncompletedTicketList();
        //then
        adminPanel.updatePrices(updatePricesRequest);
    }


    private void prepareCreateShowsRequestInstanceWithDates() {
        anyCreateShowsRequest = new CreateShowsRequest();
        anyCreateShowsRequest.setShows(prepareManyShowsDtoWithDates());
    }

    private ManyShowsDto prepareManyShowsDtoWithDates() {
        Collection<LocalDateTime> dates = prepareDates();
        return new ManyShowsDto(anyCinemaId, anyMovieId, null, dates);
    }

    private Collection<LocalDateTime> prepareDates() {
        LocalDateTime date1 = LocalDateTime.of(2016, 12, 1, 8, 0);
        LocalDateTime date2 = LocalDateTime.of(2016, 12, 6, 16, 30);
        Collection<LocalDateTime> dates = Sets.newHashSet(date1, date2);
        return dates;
    }

    private void prepareCreateShowsRequestInstanceWithCalendar() {
        anyCreateShowsRequest = new CreateShowsRequest();
        anyCreateShowsRequest.setShows(prepareManyShowsDtoWithCalendar());
    }

    private ManyShowsDto prepareManyShowsDtoWithCalendar() {
        CalendarDto calendar = prepareCalendar();
        return new ManyShowsDto(anyCinemaId, anyMovieId, calendar, null);
    }

    private CalendarDto prepareCalendar() {
        LocalDate fromDate = LocalDate.of(2016, 12, 1);
        LocalDate untilDate = LocalDate.of(2016, 12, 6);
        Collection<DayOfWeek> weekDays = Sets.newHashSet(MONDAY, WEDNESDAY);
        LocalTime hour1 = LocalTime.of(8, 0);
        LocalTime hour2 = LocalTime.of(16, 30);
        Collection<LocalTime> hours = Sets.newHashSet(hour1, hour2);
        return new CalendarDto(fromDate, untilDate, weekDays, hours);
    }

    private MovieDto prepareMovieDto() {
        return new MovieDto(anyMovieTitle, anyMovieDescription, anyMovieMinAge,
                prepareMovieActors(), prepareMovieGenres(), anyMovieLength);
    }

    private Set<String> prepareMovieActors() {
        anyMovieActors.add("Jenifer Aniston");
        anyMovieActors.add("Lily James");
        anyMovieActors.add("Nicolas Cage");
        return anyMovieActors;
    }

    private Set<String> prepareMovieGenres() {
        anyMovieGenres.add("Fantasy");
        anyMovieGenres.add("Comedy");
        anyMovieGenres.add("Sci-Fi");
        return anyMovieGenres;
    }

    private UpdatePricesRequest anyUpdatePricesRequest() {
        UpdatePricesRequest updatePricesRequest = new UpdatePricesRequest();
        updatePricesRequest.setMovieId(anyMovieId);
        HashMap<String, BigDecimal> anyMap = new HashMap<>();
        anyMap.put("children", new BigDecimal(10.00));
        anyMap.put("school", new BigDecimal(12.00));
        anyMap.put("student", new BigDecimal(15.00));
        anyMap.put("regular", new BigDecimal(20.00));
        updatePricesRequest.setPrices(anyMap);
        return updatePricesRequest;
    }

    private UpdatePricesRequest anyUpdatePricesRequestIncompletedTicketList() {
        UpdatePricesRequest updatePriceRequest = new UpdatePricesRequest();
        updatePriceRequest.setMovieId(anyMovieId);
        HashMap<String, BigDecimal> map = new HashMap<>();
        map.put("children", new BigDecimal(10.00));
        map.put("student", new BigDecimal(15.00));
        updatePriceRequest.setPrices(map);
        return updatePriceRequest;
    }
}