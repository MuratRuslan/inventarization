package kg.ksucta.kgfi.inventarization.repository;

import kg.ksucta.kgfi.inventarization.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by murat on 5/8/17.
 */
public interface PlaceRepository extends JpaRepository<Place, Integer> {
    Place findByName(String name);
}
