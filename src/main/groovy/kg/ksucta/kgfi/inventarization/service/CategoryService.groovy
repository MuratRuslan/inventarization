package kg.ksucta.kgfi.inventarization.service

import kg.ksucta.kgfi.inventarization.domain.Category
import org.springframework.stereotype.Service

/**
 * Created by murat on 5/8/17.
 */
@Service
interface CategoryService {
    Set<Category> getAll();

    Category getByName(String name)
}
