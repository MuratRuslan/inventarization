package kg.ksucta.kgfi.inventarization.service

import kg.ksucta.kgfi.inventarization.domain.Person
import org.springframework.stereotype.Service

@Service
interface PersonService {
    Person getPersonById(Long id)
    Set<Person> getPersonByName(String name)
    Set<Person> getPersonByLastname(String lastname)
    Set<Person> getPersonByNameAndLastname(String name, String lastname)
    Person getPersonByLogin(String login)
}