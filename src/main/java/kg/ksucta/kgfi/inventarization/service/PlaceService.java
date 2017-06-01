package kg.ksucta.kgfi.inventarization.service;

import kg.ksucta.kgfi.inventarization.domain.Place;

import java.util.List;

/**
 * Created by samsung on 12.05.2017.
 */
public interface PlaceService extends Service<Place> {
    List<Place> getAll();

    Place getByName(String name);
}
