package kg.ksucta.kgfi.inventarization.repository;


import kg.ksucta.kgfi.inventarization.domain.Category;
import kg.ksucta.kgfi.inventarization.domain.Item;
import kg.ksucta.kgfi.inventarization.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by dronk_000 on 29.04.2017.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
    Set<Item> findByPlace(Place place);
    Set<Item> findByCategory(Category category);
    Set<Item> findByRegistrationDateBefore(Date date);
    Set<Item> findByRegistrationDateAfter(Date date);
    Set<Item> findByPurchaseDateBefore(Date date);
    Set<Item> findByPurchaseDateAfter(Date date);
    List<Item> findByName(String name);
    Item findByArticleNumber(String inventarNumber);
    List<Item> findByCostGreaterThanEqual(BigDecimal cost);
    List<Item> findByCostLessThanEqual(BigDecimal cost);
}
