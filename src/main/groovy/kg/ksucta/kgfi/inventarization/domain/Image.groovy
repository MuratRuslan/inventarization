package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table


@Entity
@Table(name = "IMAGE")
class Image {
    @Id
    @GeneratedValue()
    Long id;
    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID")
    Item item;
    @Column(name = "PATH")
    String path;
}
