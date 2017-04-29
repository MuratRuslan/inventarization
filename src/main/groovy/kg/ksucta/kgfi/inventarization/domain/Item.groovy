package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.Entity
import javax.persistence.Id

/**
 * Created by dronk_000 on 29.04.2017.
 */
@Entity
class Item {
    @Id
    Long id;
    Category category;
    Place place;
    Person responsiblePerson;
    Date purchaseDate;
    Date registrationDate;
    String description;
    String name;
    String inventarNumber;
    BigDecimal cost;
    List<Image> images;
}
