package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.*

@Entity
class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category")
    Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "place",nullable = false)
    Place place;

    @ManyToOne()
    @JoinColumn(name="responsiblePerson")
    Person responsiblePerson;
    Date purchaseDate;
    Date registrationDate;
    String description;
    String name;
    String inventarNumber;
    BigDecimal cost;

    @OneToMany(mappedBy = "item",targetEntity = Image.class)
    List<Image> images;
}
