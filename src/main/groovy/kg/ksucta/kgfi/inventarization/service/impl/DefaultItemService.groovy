package kg.ksucta.kgfi.inventarization.service.impl

import kg.ksucta.kgfi.inventarization.domain.Category
import kg.ksucta.kgfi.inventarization.domain.Item
import kg.ksucta.kgfi.inventarization.domain.Place
import kg.ksucta.kgfi.inventarization.repository.ItemRepository
import kg.ksucta.kgfi.inventarization.repository.PersonRepository
import kg.ksucta.kgfi.inventarization.service.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by dronk_000 on 30.04.2017.
 */
@Service
class DefaultItemService implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    Item getItemById(Long id) {
        itemRepository.findOne(id)
    }

    @Override
    Set<Item> getItemsInPlace(Place place) {
        itemRepository.findByPlace(place)
    }

    @Override
    Set<Item> getItemsByCategory(Category category) {
        itemRepository.findByCategory(category)
    }

    @Override
    Set<Item> getItemsByName(String name) {
        itemRepository.findByName(name)
    }

    @Override
    Set<Item> getItemsByRegistrationDateBefore(Date date) {
        itemRepository.findByRegistrationDateBefore(date)
    }

    @Override
    Set<Item> getItemsByRegistrationDateAfter(Date date) {
        itemRepository.findByRegistrationDateAfter(date)
    }

    @Override
    Set<Item> getItemsByPurchaseDateBefore(Date date) {
        itemRepository.findByPurchaseDateBefore(date)
    }

    @Override
    Set<Item> getItemsByPurchaseDateAfter(Date date) {
        itemRepository.findByRegistrationDateAfter(date)
    }

    @Override
    Item getItemByInventarNumber(String inventarNumber) {
        itemRepository.findByInventarNumber(inventarNumber)
    }

    @Override
    List<Item> getItemsByCostMoreExpensiveThan(BigDecimal cost) {
        itemRepository.findByCostGreaterThanEqual(cost)
    }

    @Override
    List<Item> getItemsByCostCheaperThan(BigDecimal cost) {
        itemRepository.findByCostLessThanEqual(cost)
    }

    @Override
    List<Item> getAll() {
        itemRepository.findAll()
    }
}
