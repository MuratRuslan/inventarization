package kg.ksucta.kgfi.inventarization.service;

import kg.ksucta.kgfi.inventarization.domain.Person;
import kg.ksucta.kgfi.inventarization.domain.Role;

import java.util.List;
import java.util.Set;

/**
 * Created by samsung on 12.05.2017.
 */

public interface PersonService {
    Person getPersonById(Long id);
    Set<Person> getPersonByName(String name);
    Set<Person> getPersonByLastname(String lastname);
    Set<Person> getPersonByNameAndLastname(String name, String lastname);
    Person getPersonByLogin(String login);
    List<Person> getAll();
    Set<Role> getRoles();
    void savePerson(Person person);
}
