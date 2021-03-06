package kg.ksucta.kgfi.inventarization.service.impl;

import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import kg.ksucta.kgfi.inventarization.domain.Item;
import kg.ksucta.kgfi.inventarization.service.ExportToDocumentService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;


@Service
public class ExportToCSVDocument implements ExportToDocumentService {
    private String comma = ",";
    private String newLIne = "\n";
    private String space = "       ";
    private String semiColon = ";";

    @Override
    public Resource export(Collection<Item> data) {
        StreamResource csv = new StreamResource(new StreamResource.StreamSource() {
            @Override
            public InputStream getStream() {
                String fullData = createCSVsyntax(data);
                ByteArrayInputStream inputStream = new ByteArrayInputStream(fullData.getBytes());
                return inputStream;
            }
        }, "inventarization.csv");
        csv.setMIMEType("text/csv");
        return csv;
    }

    public String createCSVsyntax(Collection<Item> data) {
        int index = 1;
        StringBuilder fullData = new StringBuilder("N%" + space + comma + "Article Number " + comma + "Second Article N" + comma + "Name" + comma
                + "Place" + comma + "Category" + comma + "Cost EURO" + space + comma + "Cost SOM" + comma + "Registration Date" +
                comma + "Author" + comma + "ISBN" + comma + "Project" +comma+"Purchase Date"+comma+"Description"+newLIne);
        for (Item item : data) {
            fullData.append(index + comma);
            fullData.append(item.getArticleNumber().replaceAll(comma, semiColon) + comma);
            fullData.append(getString(item.getSecondArtikelNumber()+comma));
            fullData.append(item.getName().replaceAll(comma, semiColon) + comma);
            fullData.append(getString(item.getPlace()) + comma);
            fullData.append(getString(item.getCategory()) + comma);
            fullData.append(getString(item.getCost()) + comma);
            fullData.append(item.getCostSom() + comma);
            fullData.append(item.getRegistrationDate() + comma);
//            if (item.getAuthor() == null) item.setAuthor("");
            fullData.append(getString(item.getAuthor()).replaceAll(comma, semiColon) + comma);
            fullData.append(item.getIsbn() + comma);
//            if (item.getProject()!=null)
                fullData.append(getString(item.getProject().getName())+comma);
            fullData.append(item.getPurchaseDate()+comma);
            fullData.append(item.getDescription()+comma);
//            fullData.append(getString(item.getAuthor());
            fullData.append(newLIne);
            index++;
        }
        return fullData.toString();
    }

    @Override
    public String toString() {
        return "CSV";
    }

    public String getString(Object o) {
        if(o == null) return "";
        return o.toString();
    }
}