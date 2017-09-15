package kg.ksucta.kgfi.inventarization;

import kg.ksucta.kgfi.inventarization.domain.RoleName;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.Random;

@SpringBootApplication
//@EnableCaching
public class InventarizationApplication {


    public static void main(String[] args) throws IllegalAccessException {
        SpringApplication.run(InventarizationApplication.class, args);
    }

}
