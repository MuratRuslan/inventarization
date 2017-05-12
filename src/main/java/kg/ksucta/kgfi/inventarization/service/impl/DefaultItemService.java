package kg.ksucta.kgfi.inventarization.service.impl;

import kg.ksucta.kgfi.inventarization.domain.Category;
import kg.ksucta.kgfi.inventarization.domain.Item;
import kg.ksucta.kgfi.inventarization.domain.Place;
import kg.ksucta.kgfi.inventarization.repository.ItemRepository;
import kg.ksucta.kgfi.inventarization.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by samsung on 12.05.2017.
 */
@Service
public class DefaultItemService implements ItemService {

    @Autowired private ItemRepository itemRepository;

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findOne(id);
    }

    @Override
    public Set<Item> getItemsInPlace(Place place) {
        return itemRepository.findByPlace(place);
    }

    @Override
    public Set<Item> getItemsByCategory(Category category) {
        return itemRepository.findByCategory(category);
    }

    @Override
    public List<Item> getItemsByName(String name) {
        return itemRepository.findByName(name);
    }

    @Override
    public Set<Item> getItemsByRegistrationDateBefore(Date date) {
        return itemRepository.findByRegistrationDateBefore(date);
    }

    @Override
    public Set<Item> getItemsByRegistrationDateAfter(Date date) {
        return itemRepository.findByRegistrationDateAfter(date);
    }

    @Override
    public Set<Item> getItemsByPurchaseDateBefore(Date date) {
        return itemRepository.findByPurchaseDateBefore(date);
    }

    @Override
    public Set<Item> getItemsByPurchaseDateAfter(Date date) {
        return itemRepository.findByPurchaseDateAfter(date);
    }

    @Override
    public Item getItemByArticleNumber(String inventarNumber) {
        return itemRepository.findByArticleNumber(inventarNumber);
    }

    @Override
    public List<Item> getItemsByCostMoreExpensiveThan(BigDecimal cost) {
        return itemRepository.findByCostGreaterThanEqual(cost);
    }

    @Override
    public List<Item> getItemsByCostCheaperThan(BigDecimal cost) {
        return itemRepository.findByCostLessThanEqual(cost);
    }

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }
}
