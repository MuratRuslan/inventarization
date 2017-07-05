package kg.ksucta.kgfi.inventarization.service.impl;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.datatable.DataTable;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import kg.ksucta.kgfi.inventarization.domain.Item;
import kg.ksucta.kgfi.inventarization.service.ExportToDocumentService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;


@Component
public class ExportToPDFDocument implements ExportToDocumentService {

    @Autowired
    private ExportToCSVDocument csv;

    @Override
    public Resource export(Collection<Item> data) {

        return new StreamResource(new StreamResource.StreamSource() {
            @Override
            public InputStream getStream() {
                PDDocument document = new PDDocument();
                PDPage page = new PDPage();
                document.addPage(page);
                ByteArrayInputStream inputStream = null;
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    PDPageContentStream contentStream = new PDPageContentStream(document, page);
                    contentStream.beginText();
                    write(contentStream, data, page, document);
                    contentStream.endText();
                    contentStream.close();
                    document.save(byteArrayOutputStream);
                    document.close();
                    inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return inputStream;
            }
        }, "inventarization.pdf");
    }

    private void write(PDPageContentStream contentStream, Collection<Item> data, PDPage page, PDDocument doc) throws IOException {
        float margin = 50;
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yStart = yStartNewPage;
        float bottomMargin = 70;

        PDFont font = PDType1Font.HELVETICA_BOLD;
        contentStream.setFont(font, 30);
        String fullData = csv.createCSVsyntax(data);
        BaseTable pdfTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true, true);
        DataTable t = new DataTable(pdfTable, page);
        t.addCsvToTable(fullData, DataTable.HASHEADER, ',');
        pdfTable.draw();
    }

    @Override
    public String toString() {
        return "PDF";
    }
}