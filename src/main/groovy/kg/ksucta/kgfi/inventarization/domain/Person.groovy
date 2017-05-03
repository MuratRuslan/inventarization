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

    @Column(nullable = false)
    String password;

    @JoinColumn(name = "role")
    @ManyToOne(targetEntity = Role.class)
    Role role;

    @OneToMany(mappedBy = "responsiblePerson", targetEntity = Item.class)
    List<Item> items;
}
