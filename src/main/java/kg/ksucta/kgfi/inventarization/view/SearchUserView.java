package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kg.ksucta.kgfi.inventarization.domain.Item;
import kg.ksucta.kgfi.inventarization.domain.Person;
import kg.ksucta.kgfi.inventarization.domain.Role;
import kg.ksucta.kgfi.inventarization.domain.RoleName;
import kg.ksucta.kgfi.inventarization.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Created by murat on 5/28/17.
 */
@SpringView(name = SearchUserView.NAME)
public class SearchUserView extends VerticalLayout implements View {
    public static final String NAME = "Search user view";
    private TextField filterTextField;
    private Collection<Person> personCollection;
    private Grid<Person> personGrid;
    private ListDataProvider<Person> dataProvider;


    @Autowired
    private PersonService personService;


    @PostConstruct
    private void init() {
        initComponents();
        addComponents(filterTextField, personGrid, buildRemoveButton());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        personCollection = personService.getAll();
        dataProvider = DataProvider.ofCollection(personCollection);
        personGrid.setDataProvider(dataProvider);
    }

    private void initComponents() {
        personGrid = new Grid<>();
        personGrid.addColumn(Person::getName).setCaption("Item name");
        personGrid.addColumn(Person::getLastname).setCaption("surname");
        personGrid.addColumn(Person::getLogin).setCaption("login");
        personGrid.setSizeFull();
        personGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        filterTextField = (TextField) buildFilter();
    }

    private Component buildFilter() {
        final TextField filter = new TextField();
        filter.addValueChangeListener(event -> {
            Collection<Person> transactions = personCollection
                    .stream().filter(person -> passesFilter(person.getName())
                            || passesFilter(person.getName())
                            || passesFilter(person.getLastname())
                            || passesFilter(person.getLogin())).collect(Collectors.toList());

            dataProvider = DataProvider.ofCollection(transactions);
            personGrid.setDataProvider(dataProvider);
        });

        filter.setPlaceholder("Filter");
        filter.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        return filter;
    }

    private Component buildRemoveButton() {
        return new Button("remove", clickEvent -> {
            personService.remove(personGrid.getSelectedItems());
            getUI().getNavigator().navigateTo(NAME);
        });
    }


    private <T> boolean passesFilter(T subject) {
        if (subject == null) {
            return false;
        }
        return subject.toString().trim().toLowerCase()
                .contains(filterTextField.getValue().toLowerCase());
    }


}
