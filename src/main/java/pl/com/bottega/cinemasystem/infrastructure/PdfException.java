package pl.com.bottega.cinemasystem.infrastructure;

public class PdfException extends RuntimeException {

    public PdfException(Exception e) {
        super(e);
    }
}
