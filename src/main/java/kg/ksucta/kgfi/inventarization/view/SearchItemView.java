package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.Registration;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.themes.ValoTheme;
import kg.ksucta.kgfi.inventarization.domain.Category;
import kg.ksucta.kgfi.inventarization.domain.Item;
import kg.ksucta.kgfi.inventarization.domain.Place;
import kg.ksucta.kgfi.inventarization.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Created by samsung on 10.05.2017.
 */
@SpringView(name = SearchItemView.NAME)
public class SearchItemView extends VerticalLayout implements View {

    public final static String NAME = "";
    private TextField filterTextField;
    private Collection<Item> itemCollection;
    private Grid<Item> items;
    private ListDataProvider<Item> dataProvider;

    @Autowired
    private ItemService itemService;


    @PostConstruct
    private void init() {
        initComponents();
        addComponents(filterTextField, items);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        itemCollection = itemService.getAll();
        dataProvider = DataProvider.ofCollection(itemCollection);
        items.setDataProvider(dataProvider);
    }

    private void initComponents() {
        items = new Grid<>();
        TextField itemName = new TextField("Name");
        items.addColumn(Item::getName).setCaption("Item name").setEditorComponent(itemName, Item::setName);
        items.addColumn(Item::getArticleNumber).setCaption("Article number");
        items.addColumn(Item::getCategory).setCaption("Category");
        items.addColumn(Item::getPlace).setCaption("Place");
        items.addColumn(Item::getCost).setCaption("Cost");
        items.addColumn(Item::getPurchaseDate).setCaption("Purchase date");
        items.addColumn(Item::getRegistrationDate).setCaption("Registration date");
        items.addColumn(Item::getDescription).setCaption("Description");
        items.setSizeFull();
        items.getEditor().setEnabled(true);
        items.getEditor().addSaveListener(editorSaveEvent ->
                itemService.saveItem(editorSaveEvent.getBean()));
        filterTextField = (TextField) buildFilter();
    }

    private Component buildFilter() {
        final TextField filter = new TextField();
        filter.addValueChangeListener(event -> {
            Collection<Item> transactions = itemCollection
                    .stream().filter(item -> passesFilter(item.getName())
                            || passesFilter(item.getArticleNumber())
                            || passesFilter(item.getCategory())
                            || passesFilter(item.getPlace())).collect(Collectors.toList());

            dataProvider = DataProvider.ofCollection(transactions);
            dataProvider.addSortComparator(Comparator
                    .comparing(Item::getRegistrationDate).reversed()::compare);
        });

        filter.setPlaceholder("Filter");
        filter.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        return filter;
    }

    private <T> boolean passesFilter(T subject) {
        if(subject == null) {
            return false;
        }
        return subject.toString().trim().toLowerCase()
                .contains(filterTextField.getValue().toLowerCase());
    }


}
