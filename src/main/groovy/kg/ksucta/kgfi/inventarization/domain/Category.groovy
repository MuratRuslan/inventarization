package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.*

@Entity
@Table(name = "category")
class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false,unique = true)
    String name;
    String description;
    @OneToMany(mappedBy = "category",targetEntity = Item.class)
    List<Item> items;
}
