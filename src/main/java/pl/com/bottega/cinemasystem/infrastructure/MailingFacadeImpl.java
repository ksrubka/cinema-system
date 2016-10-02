package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import pl.com.bottega.cinemasystem.api.MailingFacade;
import pl.com.bottega.cinemasystem.api.PdfFacade;
import pl.com.bottega.cinemasystem.domain.Reservation;

@Component
public class MailingFacadeImpl implements MailingFacade {

    private PdfFacade pdfFacade;
    private MailSender mailSender;

    public MailingFacadeImpl(PdfFacade pdfFacade, MailSender mailSender) {
        this.pdfFacade = pdfFacade;
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(Reservation reservation) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(reservation.getCustomer().getEmail());
        msg.setText("test emial");
        msg.setSubject("test subject");
        this.mailSender.send(msg);
    }
}
