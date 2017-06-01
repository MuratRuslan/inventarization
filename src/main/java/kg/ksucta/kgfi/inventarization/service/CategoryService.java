package kg.ksucta.kgfi.inventarization.service;

import kg.ksucta.kgfi.inventarization.domain.Category;

import java.util.Collection;
import java.util.List;

/**
 * Created by samsung on 12.05.2017.
 */
public interface CategoryService extends Service<Category> {
    Category getByName(String name);
}
