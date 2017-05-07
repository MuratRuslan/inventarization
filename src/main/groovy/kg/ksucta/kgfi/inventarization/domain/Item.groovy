package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.*

@Entity
class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date purchaseDate;
    Date registrationDate;
    String description;
    String name;
    String inventarNumber;
    BigDecimal cost;
    @ManyToOne
    @JoinColumn(name = 'place')
    Place place;
    @ManyToOne
    @JoinColumn(name = 'category')
    Category category
}
