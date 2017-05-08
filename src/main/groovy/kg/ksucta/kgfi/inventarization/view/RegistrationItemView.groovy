package kg.ksucta.kgfi.inventarization.view

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Alignment
import com.vaadin.ui.Label
import com.vaadin.ui.NativeSelect
import com.vaadin.ui.TextField

import javax.annotation.PostConstruct

/**
 * Created by murat on 5/8/17.
 */
@SpringView(name = RegistrationItemView.NAME)
public class RegistrationItemView extends HelperLayout implements View{

    final static String NAME = "RegistrationItemView ";
    Label header;
    TextField itemNumber;
    TextField name;
    NativeSelect<String> category;


    @PostConstruct
    void init() {
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        header = addHeader("Item Registration");
        itemNumber = addTextField("Item number");
        name = addTextField("Name");

        List<String> mockList = new ArrayList<>();
        mockList.add("one");
        mockList.add("two");
        mockList.add("three");

        category = addNativeSelect("Choose category", mockList);
        addNativeSelect("Place", mockList);
        addNativeSelect("Responsible person", mockList);

        TextField cost = addTextField("Cost");

        addDateField("Purchase date");
        addDateField("Registration date");

        addTextArea("Description","Type here description of the item");
        addButton("Save");
        addButton("Cancel");

    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}

