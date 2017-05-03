package kg.ksucta.kgfi.inventarization.service.impl.export

class DefaultCsvExportService extends AbstractExportToDocumentService {

    final String NEW_LINE_SEPARATOR = "\n";
    final String COMMA_DELIMETER = ",";

    DefaultCsvExportService(Collection collection) {
        super(collection)
    }

    @Override
    void exportToOutputStream(OutputStream outputStream) {

        outputStream.flush()
        outputStream.close()
    }
}
