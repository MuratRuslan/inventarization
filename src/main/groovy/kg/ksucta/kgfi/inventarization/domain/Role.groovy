package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.*

@Entity
@Table(name = "ROLE")
class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    RoleName name;

    String description;

    @ManyToMany(mappedBy = "roles")
    List<Person> persons;
}