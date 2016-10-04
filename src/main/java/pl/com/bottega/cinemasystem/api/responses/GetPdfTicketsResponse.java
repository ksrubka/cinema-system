package pl.com.bottega.cinemasystem.api.responses;

public class GetPdfTicketsResponse {

    private String pdfURL;

    public GetPdfTicketsResponse(String pdfURL) {
        this.pdfURL = pdfURL;
    }
}
