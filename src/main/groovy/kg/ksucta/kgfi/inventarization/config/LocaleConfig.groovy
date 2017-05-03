package kg.ksucta.kgfi.inventarization.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource

@Configuration
class LocaleConfig {

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("../../../resources/locales/test");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
