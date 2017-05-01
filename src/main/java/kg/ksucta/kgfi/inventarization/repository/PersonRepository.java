package kg.ksucta.kgfi.inventarization.repository;

import kg.ksucta.kgfi.inventarization.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by dronk_000 on 29.04.2017.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Set<Person> findByName(String name);
    Set<Person> findByLastname(String lastname);
    Set<Person> findByNameAndLastname(String name, String lastname);
    Person findByLogin(String login);
}
