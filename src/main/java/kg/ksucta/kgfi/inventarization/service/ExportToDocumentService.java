package kg.ksucta.kgfi.inventarization.service;

import com.vaadin.server.Resource;
import kg.ksucta.kgfi.inventarization.domain.Item;

import java.io.IOError;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by murat on 5/31/17.
 */
public interface ExportToDocumentService {
    Resource export(Collection<Item> data);
}
