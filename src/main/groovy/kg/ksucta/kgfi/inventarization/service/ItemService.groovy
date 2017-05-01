package kg.ksucta.kgfi.inventarization.service

import kg.ksucta.kgfi.inventarization.domain.Category
import kg.ksucta.kgfi.inventarization.domain.Item
import kg.ksucta.kgfi.inventarization.domain.Place
import org.springframework.stereotype.Service

/**
 * Created by dronk_000 on 29.04.2017.
 */
@Service
interface ItemService {
    Item getItemById(Long id)
    Set<Item> getItemsInPlace(Place place)
    Set<Item> getItemsByCategory(Category category)
    Set<Item> getItemsByName(String name)
    Set<Item> getItemsByRegistrationDateBefore(Date date)
    Set<Item> getItemsByRegistrationDateAfter(Date date)
    Set<Item> getItemsByPurchaseDateBefore(Date date)
    Set<Item> getItemsByPurchaseDateAfter(Date date)
    Item getItemByInventarNumber(String inventarNumber)
    List<Item> getItemsByCostMoreExpensiveThan(BigDecimal cost)
    List<Item> getItemsByCostCheaperThan(BigDecimal cost)
}
