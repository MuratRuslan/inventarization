package kg.ksucta.kgfi.inventarization.service

interface ExportToDocumentService<T> {
    void exportToOutputStream(OutputStream outputStream)
}