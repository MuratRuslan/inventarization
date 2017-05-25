package kg.ksucta.kgfi.inventarization.domain;

import javax.persistence.*;

/**
 * Created by samsung on 12.05.2017.
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    private RoleName name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.name();
    }
}
