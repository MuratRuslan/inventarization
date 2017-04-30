package kg.ksucta.kgfi.inventarization.repository;

import kg.ksucta.kgfi.inventarization.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dronk_000 on 29.04.2017.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

}
