package pl.com.bottega.cinemasystem.infrastructure;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import pl.com.bottega.cinemasystem.api.facades.MailingFacade;
import pl.com.bottega.cinemasystem.api.facades.PdfFacade;
import pl.com.bottega.cinemasystem.domain.Reservation;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailingFacadeImpl implements MailingFacade {

    private PdfFacade pdfFacade;
    private JavaMailSenderImpl mailSender;

    public MailingFacadeImpl(PdfFacade pdfFacade, JavaMailSenderImpl mailSender) {
        this.pdfFacade = pdfFacade;
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(Reservation reservation) {
        String cinemaNameAndCity = reservation.getShow().getCinema().getCity() +
                " " + reservation.getShow().getCinema().getName();
        String message = "You have successfully made a reservation for a movie " +
                reservation.getShow().getMovie().getTitle() + " in " +
                cinemaNameAndCity + ".\n" +
                "You can find Your ticket in the attachments.\n\n" +
                "Best regards\n\n" + cinemaNameAndCity;
        String pdfPath = pdfFacade.createPdf(reservation);
        MimeMessage msg = prepareMimeMessage(reservation, cinemaNameAndCity, message, pdfPath);
        this.mailSender.send(msg);
    }

    private MimeMessage prepareMimeMessage(Reservation reservation, String cinemaNameAndCity,
                                           String message, String pdfPath) {
        MimeMessage msg = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(reservation.getCustomer().getEmail());
            helper.setText(message);
            helper.setSubject("Your ticket to the movie in " + cinemaNameAndCity);
            helper.addAttachment(reservation.getNumber().getNumber() + ".pdf", new File(pdfPath));
        } catch (MessagingException e) {
            throw new MailingException(e);
        }
        return msg;
    }
}
