package pl.com.bottega.cinemasystem.infrastructure;

public class MailingException extends RuntimeException {

    public MailingException(Exception e) {
        super(e);
    }
}
