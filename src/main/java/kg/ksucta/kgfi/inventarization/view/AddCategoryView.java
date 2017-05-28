package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kg.ksucta.kgfi.inventarization.domain.Category;
import kg.ksucta.kgfi.inventarization.domain.RoleName;
import kg.ksucta.kgfi.inventarization.repository.PersonRepository;
import kg.ksucta.kgfi.inventarization.service.CategoryService;
import kg.ksucta.kgfi.inventarization.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Created by Baktiyar on 24.05.2017.
 */
@SpringView(name=AddCategoryView.NAME)
public class AddCategoryView extends VerticalLayout implements View {

    public final static String NAME = "Add Category";
    private Label header;
    private TextField name;
    private TextArea categoryDescription;
    private Button save;
    private Binder<Category> binder;

    @Autowired
    private CategoryService categoryService;

    @PostConstruct
    void init() {
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        header = new Label("Create category");
        name = new TextField("Name");
        categoryDescription = new TextArea("Description", "Type here description of the category");
        save = new Button("Save");
        save.setEnabled(false);
        initBinder();

        save.addClickListener(event -> {
            saveCategory();
            Notification.show("Success");
        });
        binder.addStatusChangeListener(
                event -> save.setEnabled(binder.isValid()));

        addComponents(header, name, categoryDescription, save);

    }



    private void initBinder(){
        binder = new Binder<>();
        binder.forField(name)
                .asRequired("Name may not be empty")
                .bind(Category::getName, Category::setName);
        binder.forField(categoryDescription)
                .bind(Category::getDescription, Category::setDescription);
        binder.addStatusChangeListener(
                event -> save.setEnabled(binder.isValid()));
        binder.setBean(new Category());
    }

    @Transactional
    private void saveCategory(){
        categoryService.saveCategory(binder.getBean());
        getUI().getNavigator().navigateTo(this.NAME);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
