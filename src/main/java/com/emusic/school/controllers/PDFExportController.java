package com.emusic.school.controllers;

import com.emusic.school.dtos.MerchTicketDTO;
import com.emusic.school.models.Ticket;
import com.emusic.school.services.PDFGeneratorService;
import com.emusic.school.services.TicketService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PDFExportController {

    @Autowired
    private PDFGeneratorService pdfGeneratorService;

    @Autowired
    private TicketService ticketService;

    public PDFExportController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/pdf/generate/{idTicket}")
    public void generatePDF(HttpServletResponse response, @PathVariable Long idTicket, @RequestBody List<MerchTicketDTO> merchTicketDTOS) throws IOException, DocumentException {
        Ticket ticket = ticketService.findById(idTicket);
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response, merchTicketDTOS, ticket);
    }

}