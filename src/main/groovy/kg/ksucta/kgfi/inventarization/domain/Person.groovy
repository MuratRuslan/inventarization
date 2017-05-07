package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.*

@Entity
class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String lastname;

    @Column(unique = true)
    String login;

    String password;

    @ManyToMany
    @JoinTable(name = "personRoles"
            , joinColumns = @JoinColumn(name = "personId", referencedColumnName = "id", nullable = false)
            , inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id", nullable = false)
    )
    List<Role> roles;
}
