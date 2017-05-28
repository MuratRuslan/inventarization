package kg.ksucta.kgfi.inventarization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InventarizationApplication {


	public static void main(String[] args) throws IllegalAccessException {
		SpringApplication.run(InventarizationApplication.class, args);
	}
}
