package pl.com.bottega.cinemasystem.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.com.bottega.cinemasystem.domain.ShowsRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class CreateShowsRequestTest {

    @Mock
    private ShowsRepository showsRepository;

    @Mock
    private ManyShowsDto manyShowsDto;

    private CreateShowsRequest createShowsRequest;

    @JsonFormat(pattern="yyyy/MM/dd HH:mm")
    private Collection<LocalDateTime> anyDatesList = new HashSet<>();

    private Long anyMovieId = 15L;
    private Long anyCinemaId = 16L;
    private CalendarDto anyCalendar;
    private LocalDateTime anyDate = LocalDateTime.of(2016, 9, 21, 20, 50);


    @Before
    public void setUp() {
        createShowsRequest = new CreateShowsRequest();
        anyDatesList.add(anyDate);
    }

    @Test
    public void shouldValidateShowWithCorrectShowDto() {
        //given
        createShow();
        //when
        createShowsRequest.validate();
        //then
        assertShowTest();

    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateShowWithMovieIdAsNull() {
        //given
        createShowsRequestInstance(null, anyCinemaId, anyDatesList, anyCalendar);
        //when
        createShowsRequest.validate();
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldNotValidateShowWithDateAsNull() {
        //given
        createShowsRequestInstance(anyMovieId, anyCinemaId, null, anyCalendar);
        //when
        createShowsRequest.validate();
    }

    private void createShow() {
        createShowsRequestInstance(anyMovieId, anyCinemaId, anyDatesList, anyCalendar);
    }

    private void assertShowTest() {
        assertEquals(manyShowsDto.getMovieId(), createShowsRequest.getMovieId());
        assertEquals(manyShowsDto.getCinemaId(), createShowsRequest.getCinemaId());
        assertEquals(manyShowsDto.getDates(), createShowsRequest.getDates());
        assertEquals(manyShowsDto.getCalendar(), createShowsRequest.getCalendar());
    }

    private void createShowsRequestInstance(Long movieId, Long cinemaId, Collection<LocalDateTime> dates, CalendarDto calendar) {
        manyShowsDto = new ManyShowsDto();
        createShowsRequest.setShows(manyShowsDto);
        manyShowsDto.setMovieId(movieId);
        manyShowsDto.setCinemaId(cinemaId);
        manyShowsDto.setDates(dates);
        manyShowsDto.setCalendar(calendar);
    }
}