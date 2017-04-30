package kg.ksucta.kgfi.inventarization.repository;

import kg.ksucta.kgfi.inventarization.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dronk_000 on 29.04.2017.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
