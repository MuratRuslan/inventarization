package kg.ksucta.kgfi.inventarization.view

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Alignment
import com.vaadin.ui.Button
import com.vaadin.ui.DateField
import com.vaadin.ui.Label
import com.vaadin.ui.NativeSelect
import com.vaadin.ui.TextArea
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout
import kg.ksucta.kgfi.inventarization.domain.Item
import kg.ksucta.kgfi.inventarization.service.CategoryService
import kg.ksucta.kgfi.inventarization.service.ItemService
import kg.ksucta.kgfi.inventarization.service.PersonService
import kg.ksucta.kgfi.inventarization.service.PlaceService
import org.flywaydb.core.internal.util.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

import javax.annotation.PostConstruct
import java.time.LocalDate

/**
 * Created by murat on 5/8/17.
 */
@SpringView(name = RegistrationItemView.NAME)
public class RegistrationItemView extends VerticalLayout implements View{

    final static String NAME = "RegistrationItemView ";
    Label header;
    TextField itemNumber;
    TextField name;
    TextField cost;
    NativeSelect<String> category;
    NativeSelect<String> place;
    DateField purchaseDate;
    DateField registrationDate;
    TextArea itemDescription;
    Button save;


    @Autowired
    CategoryService categoryService

    @Autowired
    PlaceService placeService

    @Autowired
    ItemService itemService

    @PostConstruct
    void init() {
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        header = new Label("Item Registration");
        itemNumber = new TextField("Item number");
        name = new TextField("Name");
        category = new NativeSelect<>("Choose category");
        place = new NativeSelect<>("Place");
        cost = new TextField("Cost");

        purchaseDate = new DateField("Purchase date");
        registrationDate =new DateField("Registration date");

        itemDescription = new TextArea("Description", "Type here description of the item");
        save = new Button("Save")
        save.addClickListener({saveItem()})


        addComponents(header, itemNumber, name, category, place, cost,
                purchaseDate, registrationDate, itemDescription, save)

    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        category.setItems(categoryService.all.collect({it.name}))
        place.setItems(placeService.all.collect({it.name}))
    }

    @Transactional
    boolean saveItem() {
        Item item = [name: name.value, inventarNumber: itemNumber.value,
                       category: categoryService.getByName(category.value),
                        place: placeService.getByName(place.value),
                        cost: cost.value as BigDecimal, purchaseDate: purchaseDate.value,
                        registrationDate: registrationDate.value as Date,
                        description: itemDescription.value as Date]
        itemService.saveItem(item)
    }
}

