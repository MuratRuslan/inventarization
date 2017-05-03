package kg.ksucta.kgfi.inventarization.util

import kg.ksucta.kgfi.inventarization.domain.Item
import kg.ksucta.kgfi.inventarization.domain.Person

/**
 * Created by dronk_000 on 03.05.2017.
 */
interface FormatUtil {
    String formatPersonCollection(Collection<Person> collection)

    String formatItemCollection(Collection<Item> collection)
}