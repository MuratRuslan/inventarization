package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kg.ksucta.kgfi.inventarization.domain.Category;
import kg.ksucta.kgfi.inventarization.domain.Item;
import kg.ksucta.kgfi.inventarization.domain.Place;
import kg.ksucta.kgfi.inventarization.service.CategoryService;
import kg.ksucta.kgfi.inventarization.service.ItemService;
import kg.ksucta.kgfi.inventarization.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by samsung on 12.05.2017.
 */
@SpringView(name = RegistrationItemView.NAME)
public class RegistrationItemView extends VerticalLayout implements View {
    public final static String NAME = "RegistrationItemView ";
    private Label header;
    private TextField itemNumber;
    private TextField name;
    private TextField cost;
    private NativeSelect<Category> category;
    private ComboBox<Place> place;
    private DateField purchaseDate;
    private TextArea itemDescription;
    private Button save;


    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private ItemService itemService;

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
        save = new Button("Save");

        save.addClickListener(event->{
        try {
            saveItem();
            Notification.show("Success");
        } catch (NullPointerException e) {
            Notification.show("fields should be filled", Notification.Type.WARNING_MESSAGE);
        } catch (NumberFormatException e) {
            Notification.show("Number given not correct", Notification.Type.WARNING_MESSAGE);
        }});


        addComponents(header, itemNumber, name, category, place, cost,
                purchaseDate, itemDescription, save);

    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        category.setItems(categoryService.getAll());
        place.setItems(placeService.getAll());
    }

    @Transactional
    void saveItem() throws NumberFormatException, NullPointerException {
        Item item = new Item();
        item.setArticleNumber(itemNumber.getValue());
        item.setCategory(category.getValue());
        item.setPlace(place.getValue());
        item.setCost(BigDecimal.valueOf(Double.parseDouble(cost.getValue())));
        item.setDescription(itemDescription.getValue());
        item.setName(name.getValue());
        item.setPurchaseDate(java.sql.Date.valueOf(purchaseDate.getValue()));
        item.setRegistrationDate(new Date());
        itemService.saveItem(item);
        getUI().getNavigator().navigateTo(this.NAME);
    }
}
