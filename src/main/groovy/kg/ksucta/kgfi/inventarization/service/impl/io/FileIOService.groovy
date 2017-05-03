package kg.ksucta.kgfi.inventarization.service.impl.io

import kg.ksucta.kgfi.inventarization.service.IOService

/**
 * Created by dronk_000 on 03.05.2017.
 */
class FileIOService implements IOService {
    static String FILE_PATH;
    private FileWriter fileWriter;

    private static FileIOService instance = new FileIOService();

    private FileIOService(String filePath) {
        try {
            this.fileWriter = new FileWriter(new File(FILE_PATH));
            FILE_PATH = filePath
        } catch (IOException e) {
            this.fileWriter = null;
        }
    }

    static FileIOService me() {
        return instance;
    }

    @Override
    void write(String s) {

    }

    @Override
    String readLine() {
        ""
    }
}
