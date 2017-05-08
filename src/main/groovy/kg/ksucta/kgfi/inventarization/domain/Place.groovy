package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.*

@Entity
@Table(name="PLACE")
class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false)
    String name;
    String description;
}