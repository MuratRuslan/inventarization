package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.data.Binder;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kg.ksucta.kgfi.inventarization.domain.Person;
import kg.ksucta.kgfi.inventarization.domain.Role;
import kg.ksucta.kgfi.inventarization.service.PersonService;
import kg.ksucta.kgfi.inventarization.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.PassThroughSourceExtractor;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Baktiyar on 24.05.2017.
 */
@SpringView(name = RegistrationUserView.NAME)
public class RegistrationUserView extends VerticalLayout implements View {
    public static final String NAME="RegistrationUserView";
    private Label header;
    private TextField name;
    private TextField lastname;
    private TextField login;
    private PasswordField password;
    private Button save;
    private Binder<Person> binder;
    private CheckBoxGroup<Role> roles;

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleService;

    @PostConstruct
    void init() {
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        header = new Label("User registration");
        name = new TextField("Name");
        lastname = new TextField("Lastname");
        login = new TextField("Login");
        password = new PasswordField("Password");
        save = new Button("Save");
        roles = new CheckBoxGroup<>("Role");
        roles.setItems(roleService.getAll());
        initBinder();

        save.addClickListener(event -> {
            savePerson();
            Notification.show("Success");
        });

        addComponents(header, name, lastname, login, password, roles, save);

    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        roles.setItems(roleService.getAll());
    }

    private void initBinder() {
        binder = new Binder<>();
        binder.forField(name)
                .asRequired("Name may not be empty")
                .bind(Person::getName, Person::setName);
        binder.forField(lastname)
                .asRequired("Lastname may not be empty")
                .bind(Person::getLastname, Person::setLastname);
        binder.forField(login)
                .asRequired("Login may not be empty")
                .bind(Person::getLogin, Person::setLogin);
        binder.forField(password)
                .asRequired("Password may not be empty")
                .bind(Person::getPassword, Person::setPassword);
        binder.addStatusChangeListener(
                event -> save.setEnabled(binder.isValid()));

    }


    @Transactional
    private void savePerson() {
        Person person = getBindedPerson();
        personService.savePerson(person);
        getUI().getNavigator().navigateTo(this.NAME);
    }

    private Person getBindedPerson() {
        Person person = new Person();
        person.setName(name.getValue());
        person.setLastname(lastname.getValue());
        person.setLogin(login.getValue());
        person.setPassword(password.getValue());

        person.setRoles(new ArrayList<>(roles.getValue()));
        return person;
    }
}
