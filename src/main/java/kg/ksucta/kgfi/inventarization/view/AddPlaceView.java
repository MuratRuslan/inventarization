package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kg.ksucta.kgfi.inventarization.domain.Place;
import kg.ksucta.kgfi.inventarization.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Created by Baktiyar on 24.05.2017.
 */
@SpringView(name=AddPlaceView.NAME)
public class AddPlaceView extends VerticalLayout implements View {

    public final static String NAME = "Add Place";
    private Label header;
    private TextField name;
    private TextArea placeDescription;
    private Button save;
    private Binder<Place> binder;

    @Autowired
    PlaceService placeService;

    @PostConstruct
    void init() {
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        header = new Label("Create place");
        name = new TextField("Name");
        placeDescription= new TextArea("Description", "Type here description of the place");
        save = new Button("Save");
        initBinder();

        save.addClickListener(event -> {
            saveCategory();
            Notification.show("Success");
        });
        binder.addStatusChangeListener(
                event -> save.setEnabled(binder.isValid()));

        addComponents(header, name, placeDescription, save);

    }



    private void initBinder(){
        binder = new Binder<>();
        binder.forField(name)
                .asRequired("Name may not be empty")
                .bind(Place::getName, Place::setName);
        binder.forField(placeDescription)
                .bind(Place::getDescription, Place::setDescription);
        binder.addStatusChangeListener(
                event -> save.setEnabled(binder.isValid()));
        binder.setBean(new Place());
    }

    @Transactional
    private void saveCategory(){
        placeService.save(binder.getBean());
        getUI().getNavigator().navigateTo(this.NAME);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
