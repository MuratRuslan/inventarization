package kg.ksucta.kgfi.inventarization.repository;

import kg.ksucta.kgfi.inventarization.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by murat on 5/25/17.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
