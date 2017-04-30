package kg.ksucta.kgfi.inventarization.service.impl

import kg.ksucta.kgfi.inventarization.repository.PersonRepository
import kg.ksucta.kgfi.inventarization.service.PersonService
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by dronk_000 on 30.04.2017.
 */
class DefaultPersonService implements PersonService {

    @Autowired
    PersonRepository personRepository;

}
