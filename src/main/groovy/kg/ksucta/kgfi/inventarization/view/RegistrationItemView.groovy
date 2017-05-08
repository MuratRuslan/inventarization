package kg.ksucta.kgfi.inventarization.view

import com.vaadin.event.MouseEvents.ClickListener
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Alignment
import com.vaadin.ui.DateField
import com.vaadin.ui.Label
import com.vaadin.ui.NativeSelect
import com.vaadin.ui.TextArea
import com.vaadin.ui.TextField
import kg.ksucta.kgfi.inventarization.domain.Category
import kg.ksucta.kgfi.inventarization.domain.Item
import kg.ksucta.kgfi.inventarization.service.CategoryService
import kg.ksucta.kgfi.inventarization.service.ItemService
import org.springframework.beans.factory.annotation.Autowired

import javax.annotation.PostConstruct

/**
 * Created by murat on 5/8/17.
 */
@SpringView(name = RegistrationItemView.NAME)
public class RegistrationItemView extends HelperLayout implements View {

    final static String NAME = "RegistrationItemView ";
    Label header;
    TextField itemNumber;
    TextField name;
    TextField cost
    DateField purchaseDate;
    NativeSelect<String> category;
    NativeSelect<String> responsiblePerson;
    TextArea descrip;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ItemService itemService;

    @PostConstruct
    void init() {
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        header = addHeader("Item Registration");
        itemNumber = addTextField("Item number");
        name = addTextField("Name");

        List<String> mockList = new ArrayList<>();
        for (Category name : categoryService.findAll()) {
            mockList.add(name);
        }

        category = addNativeSelect("Choose category", mockList);
        addNativeSelect("Place", mockList);
        responsiblePerson = addNativeSelect("Responsible person", mockList);

        cost = addTextField("Cost");

        purchaseDate = addDateField("Purchase date");

        descrip = addTextArea("Description", "Type here description of the item");
        addButton("Save").addClickListener();
        addButton("Cancel");

    }

    public void saveItem() {

        Item item = Item();
        Date date = new Date();
        item.setName(name.getValue())
        item.setRegistrationDate(date)
        item.setInventarNumber(itemNumber.getValue())
        item.setDescription(descrip.getValue())
        item.setCost(Integer.parseInt(cost.getValue()))
        for (Category c:categoryService.findAll()){
            if (c.getName().equalsIgnoreCase(category.getValue())){
                item.setCategory(c);
            }
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}

