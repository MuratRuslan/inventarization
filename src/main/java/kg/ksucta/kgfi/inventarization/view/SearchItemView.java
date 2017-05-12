package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import kg.ksucta.kgfi.inventarization.domain.Item;
import kg.ksucta.kgfi.inventarization.service.CategoryService;
import kg.ksucta.kgfi.inventarization.service.ItemService;
import kg.ksucta.kgfi.inventarization.service.PersonService;
import kg.ksucta.kgfi.inventarization.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.haijian.PdfExporter;


import javax.annotation.PostConstruct;

/**
 * Created by samsung on 10.05.2017.
 */
@SpringView(name = SearchItemView.NAME)
public class SearchItemView extends VerticalLayout implements View {

    public final static String NAME = "SearchItemView";
    private TextField filterTextField;
    private Grid<Item> items;

    @Autowired
    private ItemService itemService;
    @Autowired
    private PersonService personService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PlaceService placeService;


    @PostConstruct
    private void init() {
        initComponents();
        addComponents(filterTextField, items);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    void initComponents() {
        items = new Grid<>();
        items.addColumn(Item::getName).setCaption("Item name");
        items.addColumn(Item::getArticleNumber).setCaption("Article number");
        items.addColumn(Item::getCategory).setCaption("Category");
        items.addColumn(Item::getPlace).setCaption("Place");
        items.addColumn(Item::getCost).setCaption("Cost");
        items.addColumn(Item::getPurchaseDate).setCaption("Purchase date");
        items.addColumn(Item::getRegistrationDate).setCaption("Registration date");
        items.addColumn(Item::getDescription).setCaption("Description");
        items.setSizeFull();
        ListDataProvider<Item> dataProvider = DataProvider.ofCollection(itemService.getAll());
        items.setDataProvider(dataProvider);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        filterTextField = new TextField();
        filterTextField.setPlaceholder("Filter");
        filterTextField.addValueChangeListener(event -> {
            dataProvider.setFilter(Item::getName, name -> {
                String nameLower = name == null ? ""
                        : name.toLowerCase();
                String filterLower = event.getValue()
                        .toLowerCase();
                return nameLower.contains(filterLower);
            });
        });
    }

}
