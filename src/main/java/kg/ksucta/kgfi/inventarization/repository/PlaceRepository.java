package kg.ksucta.kgfi.inventarization.repository;

import kg.ksucta.kgfi.inventarization.domain.Place;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by murat on 5/8/17.
 */
@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {
    Place findByName(String name);

    @Override
    //@Cacheable("places")
    List<Place> findAll();
}
