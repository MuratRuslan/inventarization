package kg.ksucta.kgfi.inventarization.service.impl

import kg.ksucta.kgfi.inventarization.domain.Category
import kg.ksucta.kgfi.inventarization.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by temirlan on 5/8/17.
 */
class DefaultCategoryService implements CategoryService{

    @Autowired
    CategoryRepository repository;

    @Override
    List<Category> getAll() {
        return repository.findAll();
    }
}
