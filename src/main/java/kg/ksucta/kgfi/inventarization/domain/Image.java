package kg.ksucta.kgfi.inventarization.domain;

import javax.persistence.*;

/**
 * Created by samsung on 12.05.2017.
 */
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "PATH")
    private String path;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
