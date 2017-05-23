package kg.ksucta.kgfi.inventarization;

import kg.ksucta.kgfi.inventarization.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

import java.lang.reflect.Field;

@SpringBootApplication()
public class InventarizationApplication {

	public static void main(String[] args) throws IllegalAccessException {
		SpringApplication.run(InventarizationApplication.class, args);
	}
}
