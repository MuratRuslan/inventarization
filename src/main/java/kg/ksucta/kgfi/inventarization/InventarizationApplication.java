package kg.ksucta.kgfi.inventarization;

import kg.ksucta.kgfi.inventarization.config.VaadinSessionSecurityContextHolderStrategy;
import kg.ksucta.kgfi.inventarization.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;
import java.lang.reflect.Field;

@SpringBootApplication()
public class InventarizationApplication {


	public static void main(String[] args) throws IllegalAccessException {
		SpringApplication.run(InventarizationApplication.class, args);
	}
}
