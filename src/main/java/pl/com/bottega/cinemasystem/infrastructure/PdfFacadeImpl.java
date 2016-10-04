package pl.com.bottega.cinemasystem.infrastructure;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import pl.com.bottega.cinemasystem.api.facades.PdfFacade;
import pl.com.bottega.cinemasystem.domain.Reservation;
import pl.com.bottega.cinemasystem.domain.Ticket;

import java.io.FileOutputStream;
import java.util.Set;

@Component
public class PdfFacadeImpl implements PdfFacade {

    private static final String HOME_PATH = System.getProperty("user.home") + "/tickets/";

    @Override
    public String createPdf(Reservation reservation) {
        Set<Ticket> tickets = TicketsFactory.create(
                reservation.getShow(), reservation.getBookedSeats(), reservation.getTickets());
        return generatePdfTickets(tickets, reservation);
    }

    private String generatePdfTickets(Set<Ticket> tickets, Reservation reservation) {
        String path = HOME_PATH + reservation.getNumber().getNumber() + ".pdf";
        Document document = new Document();
        preparePdfWriter(document, path);
        document.open();
        prepareDocument(tickets, reservation, document);
        document.close();
        return path;
    }

    private void preparePdfWriter(Document document, String path) {
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path));
        } catch (Exception e) {
            throw new PdfException(e);
        }
    }

    private void prepareDocument(Set<Ticket> tickets, Reservation reservation, Document document) {
        Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
        int chapterNumber = 1;
        for (Ticket ticket : tickets) {
            Chapter chapter = new Chapter(new Paragraph(""), chapterNumber);
            chapter.add(new Paragraph("***********************************************", paragraphFont));
            chapter.add(new Paragraph(ticket.getShow().getCinema().getCity() + " " +
                    ticket.getShow().getCinema().getName()));
            chapter.setNumberDepth(0);
            chapter.add(new Paragraph(ticket.getShow().getMovie().getTitle(), paragraphFont));
            chapter.add(new Paragraph(
                    "Date: " +
                            ticket.getShow().getDate().toString(), paragraphFont));
            chapter.add(new Paragraph(
                    "Time: " +
                            ticket.getShow().getTime().toString(), paragraphFont));
            chapter.add(new Paragraph(
                    "Duration: " +
                            ticket.getShow().getMovie().getLength().toString() + " minutes", paragraphFont));
            chapter.add(new Paragraph(
                    "Row: " +
                            ticket.getSeat().getRow(), paragraphFont));
            chapter.add(new Paragraph(
                    "Seat number: " +
                            ticket.getSeat().getNumber(), paragraphFont));
            chapter.add(new Paragraph(
                    "Ticket kind: " +
                            ticket.getTicketPrice().getType(), paragraphFont));
            chapter.add(new Paragraph(
                    "Reservation number: " +
                            reservation.getNumber().getNumber(), paragraphFont));
            chapter.add(new Paragraph("***********************************************", paragraphFont));
            chapter.add(new Paragraph(""));

            try {
                document.add(chapter);
            } catch (DocumentException e) {
                throw new PdfException(e);
            }
            chapterNumber++;
        }
    }
}
