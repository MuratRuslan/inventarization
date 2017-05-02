package kg.ksucta.kgfi.inventarization.service.impl.export

import kg.ksucta.kgfi.inventarization.service.ExportToDocumentService

/**
 * Created by dronk_000 on 01.05.2017.
 */
abstract class AbstractExportToDocumentService<T> implements ExportToDocumentService {

    Collection<T> collection

    AbstractExportToDocumentService(Collection<T> collection) {
        this.collection = collection
    }


}
