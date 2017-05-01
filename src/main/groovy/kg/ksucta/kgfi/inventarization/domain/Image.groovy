package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.*

@Entity
@Table(name = "IMAGE")
class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID")
    Item item;
    @Column(name = "PATH")
    String path;
}