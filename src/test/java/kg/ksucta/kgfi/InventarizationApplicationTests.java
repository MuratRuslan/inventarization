package kg.ksucta.kgfi;

import kg.ksucta.kgfi.inventarization.InventarizationApplication;
import kg.ksucta.kgfi.inventarization.domain.Person;
import kg.ksucta.kgfi.inventarization.repository.PersonRepository;
import kg.ksucta.kgfi.inventarization.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class InventarizationApplicationTests {

	@Autowired
	PersonRepository personRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	@Transactional
	@Rollback
	public void personRepositoryTest() {
		Person person = personRepository.findByLogin("murat");
		if(null != person)
			System.out.println(person.getId() + "\n" +
				person.getLogin() + "\n" +
				person.getPassword());
	}

}
