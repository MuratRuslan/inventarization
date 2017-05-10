package kg.ksucta.kgfi.inventarization.view

import com.vaadin.data.provider.DataProvider
import com.vaadin.data.provider.ListDataProvider
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Grid
import com.vaadin.ui.NativeSelect
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout
import kg.ksucta.kgfi.inventarization.domain.Item
import kg.ksucta.kgfi.inventarization.service.CategoryService
import kg.ksucta.kgfi.inventarization.service.ItemService
import kg.ksucta.kgfi.inventarization.service.PersonService
import kg.ksucta.kgfi.inventarization.service.PlaceService
import org.springframework.beans.factory.annotation.Autowired

import javax.annotation.PostConstruct

@SpringView(name = SearchItemView.NAME)
class SearchItemView extends VerticalLayout implements View {

    final static String NAME = 'SearchItemView'
    TextField filterTextField
    Grid<Item> items

    @Autowired ItemService itemService
    @Autowired PersonService personService
    @Autowired CategoryService categoryService
    @Autowired PlaceService placeService


    @PostConstruct
    private void init() {
        filterTextField = new TextField()
        initComponents()
        addComponents(filterTextField)
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    void initComponents() {
        items = new Grid<>()
        items.addColumn(Item.&getName).setCaption('Item name');
        ListDataProvider<Item> dataProvider = DataProvider.ofCollection(itemService.all)
        items.setDataProvider(dataProvider)

        filterTextField = new TextField()
        filterTextField.setPlaceholder('Filter')

    }

}
