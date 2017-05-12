package kg.ksucta.kgfi.inventarization.view

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Alignment
import com.vaadin.ui.Button
import com.vaadin.ui.Label
import com.vaadin.ui.Notification
import com.vaadin.ui.TextArea
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout
import kg.ksucta.kgfi.inventarization.domain.Place
import kg.ksucta.kgfi.inventarization.service.PlaceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

import javax.annotation.PostConstruct

/**
 * Created by Baktiyar on 12.05.2017.
 */
@SpringView(name = kg.ksucta.kgfi.inventarization.view.AddPlaceView.NAME)
class AddPlaceView extends VerticalLayout implements View{
    final static String NAME =  "AddPlaceView";

    Label header;
    TextField name;
    TextArea placeDescription;
    Button save;

    @Autowired
    PlaceService placeService;

    @PostConstruct
    void init() {
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        header = new Label("Create place");
        name = new TextField("Name");
        placeDescription= new TextArea("Description", "Type here description of the place");
        save = new Button("Save");

        save.addClickListener({   try {
            savePLace();
            Notification.show("Success");
        } catch (NullPointerException e) {
            Notification.show("fields should be filled", Notification.Type.WARNING_MESSAGE);
        } catch (NumberFormatException e) {
            Notification.show("Number given not correct", Notification.Type.WARNING_MESSAGE);
        }
        })

        addComponents(header, name, placeDescription, save);

    }


    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        place.setItems(placeService.all);
    }
    @Transactional
    boolean savePLace() throws NumberFormatException, NullPointerException {
        Place place = [
                name        :           name.value,
                description :           placeDescription.value]
        placeService.savePLace(place);
        getUI().navigator.navigateTo(this.NAME);
    }

}
