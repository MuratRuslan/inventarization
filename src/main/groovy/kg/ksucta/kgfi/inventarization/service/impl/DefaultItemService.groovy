package kg.ksucta.kgfi.inventarization.service.impl

import kg.ksucta.kgfi.inventarization.repository.ItemRepository
import kg.ksucta.kgfi.inventarization.repository.PersonRepository
import kg.ksucta.kgfi.inventarization.service.ItemService
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by dronk_000 on 30.04.2017.
 */
class DefaultItemService implements ItemService {

    @Autowired
    ItemRepository itemRepository;


}
