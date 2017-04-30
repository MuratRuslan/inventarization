package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table


@Entity
@Table(name="PLACE")
class Place {
    @Id
    @GeneratedValue
    Long id;
    @Column()
    String name;
    String description;
    @OneToMany(mappedBy = "place")
    List<Item>items;
}
