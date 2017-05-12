package kg.ksucta.kgfi.inventarization.view

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Alignment
import com.vaadin.ui.Button
import com.vaadin.ui.ComboBox
import com.vaadin.ui.Label
import com.vaadin.ui.Notification
import com.vaadin.ui.TextArea
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout
import kg.ksucta.kgfi.inventarization.domain.Category
import kg.ksucta.kgfi.inventarization.domain.Person
import kg.ksucta.kgfi.inventarization.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

import javax.annotation.PostConstruct

/**
 * Created by Baktiyar on 11.05.2017.
 */
@SpringView(name = AddCategoryView.NAME)
class AddCategoryView extends VerticalLayout implements View{
    final static String NAME = "AddCategoryView";

    Label header;
    TextField name;
    TextArea categoryDescription;
    Button save;

    @Autowired
    CategoryService categoryService;

    @PostConstruct
    void init() {
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        header = new Label("Create category");
        name = new TextField("Name");
        categoryDescription = new TextArea("Description", "Type here description of the category");
        save = new Button("Save");

        save.addClickListener({   try {
            saveCategory();
            Notification.show("Success");
        } catch (NullPointerException e) {
            Notification.show("fields should be filled", Notification.Type.WARNING_MESSAGE);
        } catch (NumberFormatException e) {
            Notification.show("Number given not correct", Notification.Type.WARNING_MESSAGE);
        }
        })

        addComponents(header, name, categoryDescription, save);

    }


    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        category.setItems(categoryService.all);
    }
    @Transactional
    boolean saveCategory() throws NumberFormatException, NullPointerException {
        Category category = [
                name        :                      name.value,
                categoryDescription :              description.value]
        categoryService.saveCategory(category);
        getUI().navigator.navigateTo(this.NAME);
    }
}
