package kg.ksucta.kgfi.inventarization.service.impl

import kg.ksucta.kgfi.inventarization.domain.Category
import kg.ksucta.kgfi.inventarization.repository.CategoryRepository
import kg.ksucta.kgfi.inventarization.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by murat on 5/8/17.
 */
@Service
class DefaultCategoryService implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository

    @Override
    Set<Category> getAll() {
        categoryRepository.findAll()
    }

    @Override
    Category getByName(String name) {
        categoryRepository.findByName(name)
    }
}
