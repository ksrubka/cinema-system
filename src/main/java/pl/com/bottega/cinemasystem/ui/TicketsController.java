package pl.com.bottega.cinemasystem.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.cinemasystem.api.services.CashierPanel;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping
public class TicketsController {

    private CashierPanel cashierPanel;

    public TicketsController(CashierPanel cashierPanel) {
        this.cashierPanel = cashierPanel;
    }

    @GetMapping("/reservations/{reservationNumber}/tickets")
    public void createPdfTickets(@PathVariable String reservationNumber, HttpServletResponse response) throws IOException {
        String ticketsPath = cashierPanel.getPdfTickets(reservationNumber);
        response.setContentType("application/pdf");
        OutputStream outputStream = response.getOutputStream();
        InputStream inputStream = new FileInputStream(ticketsPath);

        int intCharacter = inputStream.read();
        while(intCharacter != -1) {
            outputStream.write(intCharacter);
            intCharacter = inputStream.read();
        }
        inputStream.close();
        outputStream.close();
    }
}
