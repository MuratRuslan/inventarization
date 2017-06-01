package kg.ksucta.kgfi.inventarization.service.impl;

import kg.ksucta.kgfi.inventarization.domain.Category;
import kg.ksucta.kgfi.inventarization.repository.CategoryRepository;
import kg.ksucta.kgfi.inventarization.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by samsung on 12.05.2017.
 */
@Service
public class DefaultCategoryService implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void remove(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public void remove(Collection<Category> categories) {
        categoryRepository.delete(categories);
    }
}
