package kg.ksucta.kgfi.inventarization.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by samsung on 12.05.2017.
 */
@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private
    Integer id;
    @Column(nullable = false)
    private
    String name;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "place")
    private
    List<Item> items;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return getName();
    }
}
