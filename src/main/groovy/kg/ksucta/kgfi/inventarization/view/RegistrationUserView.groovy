package kg.ksucta.kgfi.inventarization.view

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Alignment
import com.vaadin.ui.Button
import com.vaadin.ui.ComboBox
import com.vaadin.ui.Label
import com.vaadin.ui.Notification
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout
import kg.ksucta.kgfi.inventarization.domain.Person
import kg.ksucta.kgfi.inventarization.domain.Role
import kg.ksucta.kgfi.inventarization.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

import javax.annotation.PostConstruct

/**
 * Created by Baktiyar on 11.05.2017.
 */
@SpringView(name = RegistrationUserView.NAME)
public class RegistrationUserView extends VerticalLayout implements View{

    final static String NAME = "RegistrationUserView ";
    Label header;
    TextField name;
    TextField lastname;
    TextField login;
    TextField password;
    ComboBox<Role> role;
    Button save;

    @Autowired
    PersonService personService;

    @PostConstruct
    void init() {
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        header = new Label("User registration");
        name = new TextField("Name");
        lastname = new TextField("Lastname");
        login = new TextField("Login");
        password = new TextField("Password");
        role = new ComboBox<>("Role");
        save = new Button("Save");

        save.addClickListener({   try {
            saveItem();
            Notification.show("Success");
        } catch (NullPointerException e) {
            Notification.show("fields should be filled", Notification.Type.WARNING_MESSAGE);
        } catch (NumberFormatException e) {
            Notification.show("Number given not correct", Notification.Type.WARNING_MESSAGE);
        }
        })

        addComponents(header, name, lastname, login, password, role, save);

    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        role.setItems(personService.all);
    }

    @Transactional
    boolean saveItem() throws NumberFormatException, NullPointerException {
        Person person = [
                name            : name.value,
                lastname        : lastname.value,
                login           : login.value,
                password        : password.value]
        personService.savePerson(person);
        getUI().navigator.navigateTo(this.NAME);
    }
}
