package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kg.ksucta.kgfi.inventarization.domain.Category;
import kg.ksucta.kgfi.inventarization.domain.RoleName;
import kg.ksucta.kgfi.inventarization.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.stream.Collectors;

import static kg.ksucta.kgfi.inventarization.utils.SecurityUtils.hasRole;

/**
 * Created by murat on 6/1/17.
 */
@SpringView(name = SearchCategoryView.NAME)
public class SearchCategoryView extends VerticalLayout implements View {
    public final static String NAME = "Search Category";
    private TextField filterTextField;
    private Grid<Category> categoryGrid;

    @Autowired
    private AddCategoryView addCategoryView;

    @Autowired
    private CategoryService categoryService;

    @PostConstruct
    private void init() {
        initComponents();
        addComponents(filterTextField, categoryGrid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        categoryGrid.setDataProvider(DataProvider.ofCollection(categoryService.getAll()));
        if (hasRole(RoleName.ADMIN.name()) || hasRole(RoleName.OPERATOR.name()))
            buildRemoveButton();
    }

    private void initComponents() {
        categoryGrid = new Grid<>();
        TextField categoryName = new TextField("Name");
        categoryGrid.addColumn(Category::getName).setCaption("Name").setEditorComponent(categoryName, Category::setName);
        categoryGrid.addColumn(Category::getDescription).setCaption("Description");
        categoryGrid.setSizeFull();
        categoryGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        categoryGrid.addItemClickListener(itemClick -> showEdit(itemClick.getItem()));
        filterTextField = (TextField) buildFilter();
    }

    private Component buildFilter() {
        final TextField filter = new TextField();
        filter.addValueChangeListener(event -> {
            Collection<Category> transactions = categoryService.getAll()
                    .stream().filter(category ->
                            passesFilter(category.getName()))
                    .collect(Collectors.toList());
            categoryGrid.setDataProvider(DataProvider.ofCollection(transactions));
        });

        filter.setPlaceholder("Filter");
        filter.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        return filter;
    }

    private <T> boolean passesFilter(T subject) {
        if (subject == null) {
            return false;
        }
        return subject.toString().trim().toLowerCase()
                .contains(filterTextField.getValue().toLowerCase());
    }

    private Component buildRemoveButton() {
        Button remove = new Button("remove",
                clickEvent -> {
                    Notification.show(categoryGrid.getSelectedItems().iterator().next().getId().toString());
                    categoryService.remove(categoryGrid.getSelectedItems());
                    getUI().getNavigator().navigateTo(NAME);
                });
        addComponent(remove);
        return remove;
    }


    private void showEdit(Category category) {
        if (hasRole(RoleName.OPERATOR.name()) || hasRole(RoleName.ADMIN.name())) {
            Window editWindow = new Window("Edit category");
            editWindow.setContent(addCategoryView);
            addCategoryView.setCategory(category);
            if(getUI().getWindows().size() == 0)
                getUI().addWindow(editWindow);
            editWindow.center();
            editWindow.setHeight("70%");
            editWindow.addCloseListener(closeEvent -> {
                Collection<Category> categoryCollection = categoryService.getAll();
                categoryGrid.setDataProvider(DataProvider.ofCollection(categoryCollection));
            });
        }
    }
}
