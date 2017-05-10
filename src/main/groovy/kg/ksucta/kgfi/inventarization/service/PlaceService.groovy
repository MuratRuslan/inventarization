package kg.ksucta.kgfi.inventarization.service

import kg.ksucta.kgfi.inventarization.domain.Place

/**
 * Created by murat on 5/8/17.
 */
interface PlaceService {
    Set<Place> getAll()

    Place getByName(String name)
}