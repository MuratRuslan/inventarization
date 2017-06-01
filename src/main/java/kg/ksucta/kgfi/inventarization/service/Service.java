package kg.ksucta.kgfi.inventarization.service;

import java.util.Collection;

/**
 * Created by murat on 6/1/17.
 */
public interface Service<T> {
    Collection<T> getAll();
    void save(T item);
    void remove(T item);
    void remove(Collection<T> items);
}
