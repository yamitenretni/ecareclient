package com.javaschool.ecarerestclient.servlet;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.javaschool.ecarerestclient.controller.ClientController;
import com.javaschool.ecarerestclient.dto.ClientDTO;
import com.javaschool.ecarerestclient.dto.ContractDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Лена on 06.01.2016.
 */
//@ManagedBean
//@SessionScoped
public class PdfServlet extends HttpServlet {

//    @ManagedProperty(value="clientController")
//    private ClientController clientController;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat sfd = new SimpleDateFormat("dd.MM.yyyy");
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);
        Font tableTitleFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

        ClientController clientController = (ClientController) req.getSession().getAttribute("clientController");
        List<ClientDTO> clientList = clientController.getClientList();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, resp.getOutputStream());
            document.open();
            document.addTitle("Client report");
            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            table.getDefaultCell().setPadding(5);
            PdfPCell cell;
            String[] tableHeaders = new String[]{
                    "Name",
                    "Address",
                    "Passport",
                    "Birth date",
                    "E-mail",
                    "Blocking date",
                    "Contract number",
                    "Tariff"
            };
            for (String header : tableHeaders) {
                table.addCell(new Phrase(header, tableTitleFont));
            }

            for (ClientDTO client : clientList) {
                int contractAmount = client.getContractList().size();
                table.getDefaultCell().setRowspan(contractAmount);

                table.addCell(client.getFirstName() + " " + client.getLastName());
                table.addCell(client.getAddress());
                table.addCell(client.getPassportData());
                table.addCell(sfd.format(client.getBirthDate().getTime()));
                table.addCell(client.getEmail());
                if (client.getBlockingDate() != null) {
                    table.addCell(sfd.format(client.getBlockingDate()));
                } else {
                    table.addCell("");
                }
                table.getDefaultCell().setRowspan(1);
                for (ContractDTO contract : client.getContractList()) {
                    table.addCell(contract.getNumber());
                    table.addCell(contract.getTariff());
                }
            }
            Paragraph paragraph = new Paragraph();
            paragraph.setSpacingAfter(10);
            paragraph.setFont(titleFont);
            paragraph.add("Client report");
            document.add(paragraph);
            document.add(table);
            resp.setContentType("application/pdf");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }

//        super.doGet(req, resp);
    }
}
