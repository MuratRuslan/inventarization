package kg.ksucta.kgfi.inventarization.view

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Alignment
import com.vaadin.ui.Button
import com.vaadin.ui.ComboBox
import com.vaadin.ui.DateField
import com.vaadin.ui.Label
import com.vaadin.ui.NativeSelect
import com.vaadin.ui.Notification
import com.vaadin.ui.TextArea
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout
import kg.ksucta.kgfi.inventarization.domain.Category
import kg.ksucta.kgfi.inventarization.domain.Item
import kg.ksucta.kgfi.inventarization.domain.Place
import kg.ksucta.kgfi.inventarization.service.CategoryService
import kg.ksucta.kgfi.inventarization.service.ItemService
import kg.ksucta.kgfi.inventarization.service.PersonService
import kg.ksucta.kgfi.inventarization.service.PlaceService
import org.flywaydb.core.internal.util.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

import javax.annotation.PostConstruct


/**
 * Created by murat on 5/8/17.
 */
@SpringView(name = RegistrationItemView.NAME)
public class RegistrationItemView extends VerticalLayout implements View {

    final static String NAME = "RegistrationItemView ";
    Label header;
    TextField itemNumber;
    TextField name;
    TextField cost;
    NativeSelect<Category> category;
    ComboBox<Place> place;
    DateField purchaseDate;
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
        place = new ComboBox<>("Place");
        cost = new TextField("Cost");
        purchaseDate = new DateField("Purchase date");
        itemDescription = new TextArea("Description", "Type here description of the item");
        save = new Button("Save")

        save.addClickListener({
            try {
                saveItem()
                Notification.show("Success")
            } catch (NullPointerException e) {
                Notification.show("fields should be filled", Notification.Type.WARNING_MESSAGE)
            } catch (NumberFormatException e) {
                Notification.show("Number given not correct", Notification.Type.WARNING_MESSAGE)
            }
        })


        addComponents(header, itemNumber, name, category, place, cost,
                purchaseDate, itemDescription, save)

    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        category.setItems(categoryService.all)
        place.setItems(placeService.all)
    }

    @Transactional
    boolean saveItem() throws NumberFormatException, NullPointerException {
        Item item = [name            : name.value,
                     inventarNumber  : itemNumber.value,
                     category        : category.value,
                     place           : place.value,
                     cost            : cost.value as BigDecimal,
                     purchaseDate    : java.sql.Date.valueOf(purchaseDate.value),
                     registrationDate: new Date(),
                     description     : itemDescription.value]
        itemService.saveItem(item)
        getUI().navigator.navigateTo(this.NAME)
    }

}

