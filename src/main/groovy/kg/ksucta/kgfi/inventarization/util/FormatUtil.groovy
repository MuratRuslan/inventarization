package kg.ksucta.kgfi.inventarization.util

import kg.ksucta.kgfi.inventarization.domain.Item
import kg.ksucta.kgfi.inventarization.domain.Person

/**
 * Created by dronk_000 on 03.05.2017.
 */
interface FormatUtil {
    String formatPerson(Person person)

    String formatItem(Item item)
}