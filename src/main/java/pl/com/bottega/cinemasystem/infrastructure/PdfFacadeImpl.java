package pl.com.bottega.cinemasystem.infrastructure;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import pl.com.bottega.cinemasystem.api.PdfFacade;
import pl.com.bottega.cinemasystem.domain.Reservation;
import pl.com.bottega.cinemasystem.domain.Seat;

import java.io.FileOutputStream;
import java.util.Set;

@Component
public class PdfFacadeImpl implements PdfFacade {

    private static final String HOME_PATH = System.getProperty("user.home") + "/tickets/";

    @Override
    public String createPdf(Reservation reservation) {
        String movieTitle = reservation.getShow().getMovie().getTitle();
        Set<Seat> seats = reservation.getBookedSeats();
        reservation.getNumber();
        reservation.getShow().getDate();
        reservation.getShow().getTime();
        reservation.getShow().getMovie().getLength();

        Document document = new Document();
        String path = HOME_PATH + reservation.getNumber().getNumber() + ".pdf";
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path));
        } catch (Exception e) {
            throw new PdfException(e);
        }
        document.open();
        Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC);
        Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
        Chunk chunk = new Chunk("This is the title", chapterFont);
        Chapter chapter = new Chapter(new Paragraph(chunk), 1);
        chapter.setNumberDepth(0);
        chapter.add(new Paragraph("This is the paragraph", paragraphFont));
        try {
            document.add(chapter);
        } catch (DocumentException e) {
            throw new PdfException(e);
        }
        document.close();
        return path;
    }
}
