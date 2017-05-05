package kg.ksucta.kgfi.inventarization.config

import com.vaadin.server.VaadinService
import com.vaadin.spring.annotation.VaadinSessionScope
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by dronk_000 on 04.05.2017.
 */
@Configuration
class VaadinServletConfig {

    @Bean
    @Qualifier('servletOutputStream')
    @VaadinSessionScope
    OutputStream servletResponseOutputStream() {
        VaadinService.currentResponse.outputStream
    }
}
