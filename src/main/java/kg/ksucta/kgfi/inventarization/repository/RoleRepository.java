package kg.ksucta.kgfi.inventarization.repository;

import kg.ksucta.kgfi.inventarization.domain.Role;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by murat on 5/25/17.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Override
    @Cacheable("roles")
    List<Role> findAll();
}
