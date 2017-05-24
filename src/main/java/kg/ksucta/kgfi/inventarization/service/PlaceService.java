package kg.ksucta.kgfi.inventarization.service;

import kg.ksucta.kgfi.inventarization.domain.Place;

import java.util.List;

/**
 * Created by samsung on 12.05.2017.
 */
public interface PlaceService {
    List<Place> getAll();

    Place getByName(String name);

    void savePlace(Place place);
}
