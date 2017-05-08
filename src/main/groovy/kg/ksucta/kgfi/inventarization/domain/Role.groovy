package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.*

@Entity
class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    RoleName name;

    String description;

}