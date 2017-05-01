package kg.ksucta.kgfi.inventarization.service.impl

import kg.ksucta.kgfi.inventarization.domain.Person
import kg.ksucta.kgfi.inventarization.repository.PersonRepository
import kg.ksucta.kgfi.inventarization.service.PersonService
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by dronk_000 on 30.04.2017.
 */
class DefaultPersonService implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    Person getPersonById(Long id) {
        personRepository.findOne(id)
    }

    @Override
    Set<Person> getPersonByName(String name) {
        personRepository.findByName(name)
    }

    @Override
    Set<Person> getPersonByLastname(String lastname) {
        personRepository.findByLastname()
    }

    @Override
    Set<Person> getPersonByNameAndLastname(String name, String lastname) {
        personRepository.findByNameAndLastname(name, lastname)
    }

    @Override
    Person getPersonByLogin(String login) {
        personRepository.findByLogin(login)
    }
}
