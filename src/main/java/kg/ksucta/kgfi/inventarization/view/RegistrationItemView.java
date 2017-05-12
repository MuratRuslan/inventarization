package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.data.converter.StringToBigDecimalConverter;
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
import java.util.concurrent.atomic.DoubleAccumulator;

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
    private Binder<Item> binder;


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

        initBinder();

        save.addClickListener(event -> {
            saveItem();
            Notification.show("Success");
        });

        addComponents(header, itemNumber, name, category, place, cost,
                purchaseDate, itemDescription, save);

    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        category.setItems(categoryService.getAll());
        place.setItems(placeService.getAll());
    }

    private void initBinder() {
        binder = new Binder<>();

        binder.forField(name)
                .asRequired("Name may not be empty")
                .bind(Item::getName, Item::setName);

        binder.forField(itemNumber)
                .asRequired("article number may not be empty")
                .bind(Item::getArticleNumber, Item::setArticleNumber);

        binder.forField(category)
                .bind(Item::getCategory, Item::setCategory);

        binder.forField(place)
                .bind(Item::getPlace, Item::setPlace);

        binder.forField(cost)
                .withConverter(
                        new StringToBigDecimalConverter("Must enter a number"))
                .bind(Item::getCost, Item::setCost);

        binder.forField(purchaseDate)
                .withConverter(new LocalDateToDateConverter())
                .bind(Item::getPurchaseDate, Item::setPurchaseDate);

        binder.forField(itemDescription)
                .bind(Item::getDescription, Item::setDescription);

        binder.addStatusChangeListener(
                event -> save.setEnabled(binder.isValid()));
    }


    @Transactional
    private void saveItem() {
        Item item = binder.getBean();
        item.setRegistrationDate(new Date());
        itemService.saveItem(item);
        getUI().getNavigator().navigateTo(this.NAME);
    }
}
