package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "ROLE")
class Role {
    @Id
    @GeneratedValue
    Long id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "name")
    RoleName roleName;
    String description
    @OneToMany(mappedBy = "role")
    List<Person> persons;
}

