package com.mindhub.homebanking.utils;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mindhub.homebanking.models.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExportarTransacctions {
    private List<Transaction> listaTransactions;

    public ExportarTransacctions(List<Transaction> listaTransactions) {
        this.listaTransactions = listaTransactions;
    }

    private void headerReport(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.RED);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Type Transaction",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Description",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Amount",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("History",font));
        table.addCell(cell);
    }
    private void bodyReport(PdfPTable table){
        for (Transaction tx : listaTransactions){
            table.addCell(String.valueOf(tx.getTypeTransaction()));
            table.addCell(tx.getDescription());
            table.addCell(String.valueOf(tx.getAmount()));
            table.addCell(String.valueOf(tx.getCreationDate()));
            table.addCell(String.valueOf(tx.getAmountPost()));
        }
    }

    public void exportPdf(HttpServletResponse req) throws DocumentException, IOException {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, req.getOutputStream());

        doc.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.blue);
        font.setSize(18);
        //Contents of collection 'title' are updated, but never queried
        Paragraph title = new Paragraph("Yours transactions" + font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(title);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[]{0.5f, 0.5f, 0.5f, 0.5f, 0.5f});
        table.setWidthPercentage(100);

        headerReport(table);
        bodyReport(table);

        doc.add(table);
        doc.close();





    }

}
