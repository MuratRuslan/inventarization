package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

/**
 * Created by dronk_000 on 29.04.2017.
 */
@Entity
class Item {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY")
    Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PLACE")
    Place place;

    Person responsiblePerson;
    Date purchaseDate;
    Date registrationDate;
    String description;
    String name;
    String inventarNumber;
    BigDecimal cost;

    @OneToMany(mappedBy = "image")
    List<Image> images;
}
