package kg.ksucta.kgfi.inventarization.domain

import javax.persistence.*


@Entity
@Table(name = "image")
class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "PATH")
    String path;
    @ManyToOne
    @JoinColumn(name = 'item_id')
    Item item;
}