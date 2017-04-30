package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * Created by dronk_000 on 29.04.2017.
 */
@Entity
@Table(name = "CATEGORY")
class Category {
    @Id
    Long id;
    String name;
    String description;
    @OneToMany(mappedBy = "category")
    List<Item>items;
}
