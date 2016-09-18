package pl.com.bottega.cinemasystem.api;

import java.math.BigDecimal;
import java.util.Map;

public class UpdatePricesRequest {
    private Long movieId;
    Map<String, BigDecimal> prices; // w controllerze trzeba będzie rozparsować ciało requestu przy użyciu biblioteki Jsona i zrobić Jsona mapę
}
