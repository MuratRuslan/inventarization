package kg.ksucta.kgfi;

import kg.ksucta.kgfi.inventarization.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventarizationApplicationTests {

	@Autowired
	PersonRepository personRepository;

	@Test
	public void contextLoads() {
	}

}
