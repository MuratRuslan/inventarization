package kg.ksucta.kgfi.inventarization.service.impl.io

import com.vaadin.spring.annotation.VaadinSessionScope
import kg.ksucta.kgfi.inventarization.service.IOService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

import javax.annotation.Resource
import javax.servlet.ServletOutputStream

/**
 * Created by dronk_000 on 03.05.2017.
 */

class StreamIOService implements IOService {


    OutputStream outputStream

    StreamIOService(OutputStream outputStream) {
        this.outputStream = outputStream
    }

    @Override
    void write(String s) {
        outputStream.println(s)
    }

    @Override
    String readLine() {
        return null
    }
}
