package kg.ksucta.kgfi.inventarization.service.impl;

import kg.ksucta.kgfi.inventarization.domain.Place;
import kg.ksucta.kgfi.inventarization.repository.PlaceRepository;
import kg.ksucta.kgfi.inventarization.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by samsung on 12.05.2017.
 */
@Service
public class DefaultPlaceService implements PlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public List<Place> getAll() {
       return placeRepository.findAll();
    }

    @Override
    public Place getByName(String name) {
       return placeRepository.findByName(name);
    }

    @Override
    public void save(Place place) {
        placeRepository.save(place);
    }
}
