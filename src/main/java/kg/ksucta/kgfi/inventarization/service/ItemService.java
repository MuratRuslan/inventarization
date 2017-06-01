package kg.ksucta.kgfi.inventarization.service;

import kg.ksucta.kgfi.inventarization.domain.Category;
import kg.ksucta.kgfi.inventarization.domain.Item;
import kg.ksucta.kgfi.inventarization.domain.Place;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by samsung on 12.05.2017.
 */
public interface ItemService extends Service<Item> {
    Item getItemById(Long id);
    Set<Item> getItemsInPlace(Place place);
    Set<Item> getItemsByCategory(Category category);
    List<Item> getItemsByName(String name);
    Set<Item> getItemsByRegistrationDateBefore(Date date);
    Set<Item> getItemsByRegistrationDateAfter(Date date);
    Set<Item> getItemsByPurchaseDateBefore(Date date);
    Set<Item> getItemsByPurchaseDateAfter(Date date);
    Item getItemByArticleNumber(String inventarNumber);
    List<Item> getItemsByCostMoreExpensiveThan(BigDecimal cost);
    List<Item> getItemsByCostCheaperThan(BigDecimal cost);
}
