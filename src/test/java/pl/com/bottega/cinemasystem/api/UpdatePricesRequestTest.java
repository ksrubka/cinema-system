package pl.com.bottega.cinemasystem.api;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import pl.com.bottega.cinemasystem.domain.Movie;

import java.math.BigDecimal;
import java.util.HashMap;

public class UpdatePricesRequestTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    Movie anyMovie;

    private UpdatePricesRequest updatePricesRequest;

    @Test
    public void shouldValidatePrices(){
        updatePricesRequest = anyUpdatePriceRequest();

        updatePricesRequest.validate(anyMovie);
    }

    private UpdatePricesRequest anyUpdatePriceRequest() {
        UpdatePricesRequest updatePriceRequest = new UpdatePricesRequest();
        updatePriceRequest.setMovieId(2L);
        HashMap<String, BigDecimal> map = new HashMap<>();
        map.put("regular",new BigDecimal(20.00));
        map.put("student",new BigDecimal(14.00));
        map.put("school",new BigDecimal(12.00));
        map.put("children",new BigDecimal(10.00));
        updatePriceRequest.setPrices(map);
        return updatePriceRequest;
    }
}
