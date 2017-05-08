package kg.ksucta.kgfi.inventarization.repository;

import kg.ksucta.kgfi.inventarization.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by temirlan on 5/8/17.
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
