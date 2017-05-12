package kg.ksucta.kgfi.inventarization.service.impl;

import kg.ksucta.kgfi.inventarization.domain.Person;
import kg.ksucta.kgfi.inventarization.repository.PersonRepository;
import kg.ksucta.kgfi.inventarization.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by samsung on 12.05.2017.
 */
@Service
public class DefaultPersonService implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person getPersonById(Long id) {
       return personRepository.findOne(id);
    }

    @Override
    public Set<Person> getPersonByName(String name) {
        return   personRepository.findByName(name);
    }

    @Override
    public void savePerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public Set<Person> getPersonByLastname(String lastname) {
       return personRepository.findByLastname(lastname);
    }

    @Override
    public Set<Person> getPersonByNameAndLastname(String name, String lastname) {
       return personRepository.findByNameAndLastname(name, lastname);
    }

    @Override
    public Person getPersonByLogin(String login) {
        return personRepository.findByLogin(login);
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }
}
