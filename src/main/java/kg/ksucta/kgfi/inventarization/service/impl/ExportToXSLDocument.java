package kg.ksucta.kgfi.inventarization.service.impl;

import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import kg.ksucta.kgfi.inventarization.domain.Item;
import kg.ksucta.kgfi.inventarization.service.ExportToDocumentService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by temirlan on 6/3/17.
 */
@Service
public class ExportToXSLDocument implements ExportToDocumentService {

    @Autowired
    private ExportToCSVDocument csv;

    @Override
    public Resource export(Collection<Item> data) {
        return new StreamResource(new StreamResource.StreamSource() {
            @Override
            public InputStream getStream() {
                InputStream inputStream = null;
                try {
                    String fulldata = csv.createCSVsyntax(data);
                    Workbook wb = new HSSFWorkbook();
                    CreationHelper helper = wb.getCreationHelper();
                    Sheet sheet = wb.createSheet("LIST");

                    List<String> lines = IOUtils.readLines(new StringReader(fulldata));
                    for (int i = 0; i < lines.size(); i++) {
                        String str[] = lines.get(i).split(",");
                        Row row = sheet.createRow((short) i);
                        for (int j = 0; j < str.length; j++) {
                            row.createCell(j).setCellValue(helper.createRichTextString(str[j]));
                        }
                    }
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    wb.write(outputStream);
                    inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                    outputStream.close();
                    return inputStream;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return inputStream;
            }

        }, "inventarization.xls");
    }

    @Override
    public String toString() {
        return "XLS";
    }
}