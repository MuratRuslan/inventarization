package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

/**
 * Created by dronk_000 on 29.04.2017.
 */
@Entity
class Image {
    @Id
    Long id;
    Item item;
    String path;

}
