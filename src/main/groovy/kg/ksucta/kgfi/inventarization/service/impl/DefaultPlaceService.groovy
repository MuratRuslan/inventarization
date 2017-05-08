package kg.ksucta.kgfi.inventarization.service.impl

import kg.ksucta.kgfi.inventarization.domain.Place
import kg.ksucta.kgfi.inventarization.repository.PlaceRepository
import kg.ksucta.kgfi.inventarization.service.PlaceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by murat on 5/8/17.
 */
@Service
class DefaultPlaceService implements PlaceService {

    @Autowired
    PlaceRepository placeRepository

    @Override
    Set<Place> getAll() {
        placeRepository.findAll()
    }

    @Override
    Place getByName(String name) {
        placeRepository.findByName(name)
    }
}
