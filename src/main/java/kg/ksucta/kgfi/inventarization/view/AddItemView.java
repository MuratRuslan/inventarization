package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by samsung on 12.05.2017.
 */
@SpringView(name = AddItemView.NAME)
public class AddItemView extends VerticalLayout implements View {
    public final static String NAME = "AddItemView";
    private Label header;
    private TextField itemNumber;
    private TextField name;
    private TextField cost;
    private TextField costSom;
    private ComboBox<Category> category;
    private ComboBox<Place> place;
    private DateField purchaseDate;
    private TextArea itemDescription;
    private Button save;
    private Binder<Item> binder = new Binder<>();
    private TextField author;
    private TextField isbn;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private ItemService itemService;

    public AddItemView() {
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        header = new Label("Item Registration");
        itemNumber = new TextField("Item number");
        name = new TextField("Name");
        category = new ComboBox<>("Choose category");
        place = new ComboBox<>("Place");
        cost = new TextField("Cost EURO");
        costSom = new TextField("Cost SOM");
        author = new TextField("Author");
        isbn = new TextField("ISBN");
        isbn.setValue("");
        purchaseDate = new DateField("Purchase date");
        purchaseDate.setValue(null);
        itemDescription = new TextArea("Description");
        save = new Button("Save");
        save.setEnabled(false);
        binder.readBean(new Item());
        initBinder();

        save.addClickListener(event -> {
            try {
                saveItem();
                Notification.show("Success");
            } catch (DataIntegrityViolationException e){
                Notification.show("Article number already exist");
            } catch (Exception e) {
                Notification.show("Failed");
            }
        });

        category.addSelectionListener(e -> {
            if (e.getValue().getName().equals("Book")) {
                addComponent(author, 3);
                addComponent(isbn, 4);
                return;
            }
            removeComponent(author);
        });

        addComponents(header, itemNumber, name, category, place, cost, costSom,
                purchaseDate, itemDescription, save);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        category.setItems(categoryService.getAll());
        place.setItems(placeService.getAll());
    }

    private void initBinder() {
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
        binder.forField(costSom)
                .withConverter(
                        new StringToBigDecimalConverter("Must enter a number"))
                .bind(Item::getCostSom, Item::setCostSom);

        binder.forField(purchaseDate)
                .withConverter(new LocalDateToDateConverter())
                .bind(Item::getPurchaseDate, Item::setPurchaseDate);

        binder.forField(itemDescription)
                .bind(Item::getDescription, Item::setDescription);
        binder.forField(author)
                .bind(Item::getAuthor, Item::setAuthor);
        binder.forField(isbn)
                .bind(Item::getIsbn, Item::setIsbn);

        binder.addStatusChangeListener(
                event -> save.setEnabled(binder.isValid()));
    }


    @Transactional
    private void saveItem() throws ValidationException {
        Item item = binder.getBean();
        if (item == null) {
            item = new Item();
            binder.writeBean(item);
        }
        item.setRegistrationDate(new Date());
        itemService.save(item);
        if (getParent() instanceof Window) {
            ((Window) getParent()).close();
        } else {
            getUI().getNavigator().navigateTo(this.NAME);
        }
    }


    public void setItem(Item item) {
        binder.readBean(item);
        binder.setBean(item);
        category.setItems(categoryService.getAll());
        place.setItems(placeService.getAll());
    }
}
