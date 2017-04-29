package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.Entity
import javax.persistence.Id

/**
 * Created by dronk_000 on 29.04.2017.
 */
@Entity
class Person {
    @Id
    Long id;
    String name;
    String lastname;
    String login;
    String password;
}
