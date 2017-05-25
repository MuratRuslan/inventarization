package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kg.ksucta.kgfi.inventarization.domain.Category;
import kg.ksucta.kgfi.inventarization.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
        initBinder();

        save.addClickListener(event -> {
            saveCategory();
            Notification.show("Success");
        });

        addComponents(header, name, categoryDescription, save);

    }



    private void initBinder(){
        binder = new Binder<>();
        binder.forField(name)
                .asRequired("Name may not be empty")
                .bind(Category::getName, Category::setName);
        binder.forField(categoryDescription)
                .bind(Category::getDescription, Category::setDescription);
    }

    @Transactional
    private void saveCategory(){
        Category category = getBindedCategory();
        categoryService.saveCategory(category);
        getUI().getNavigator().navigateTo(this.NAME);
    }

    private Category getBindedCategory(){
        Category category = new Category();
        category.setName(name.getValue());
        category.setDescription(categoryDescription.getValue());
        return category;
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}