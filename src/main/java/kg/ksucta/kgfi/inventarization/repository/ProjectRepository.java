package kg.ksucta.kgfi.inventarization.repository;

import kg.ksucta.kgfi.inventarization.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by murat on 10/1/17.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
