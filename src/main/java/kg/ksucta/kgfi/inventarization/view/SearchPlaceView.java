package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kg.ksucta.kgfi.inventarization.domain.Place;
import kg.ksucta.kgfi.inventarization.domain.RoleName;
import kg.ksucta.kgfi.inventarization.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.stream.Collectors;

import static kg.ksucta.kgfi.inventarization.utils.SecurityUtils.hasRole;

/**
 * Created by murat on 7/5/17.
 */
public class SearchPlaceView extends VerticalLayout implements View {
    public final static String NAME = "Search Place";
    private TextField filterTextField;
    private Grid<Place> placeGrid;

    @Autowired
    private AddPlaceView addPlaceView;

    @Autowired
    private PlaceService placeService;

    @PostConstruct
    private void init() {
        initComponents();
        addComponents(filterTextField, placeGrid);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        placeGrid.setDataProvider(DataProvider.ofCollection(placeService.getAll()));
        if (hasRole(RoleName.ADMIN.name()) || hasRole(RoleName.OPERATOR.name()))
            buildRemoveButton();
    }

    private void initComponents() {
        placeGrid = new Grid<>();
        TextField placeName = new TextField("Name");
        placeGrid.addColumn(Place::getName).setCaption("Name").setEditorComponent(placeName, Place::setName);
        placeGrid.addColumn(Place::getDescription).setCaption("Description");
        placeGrid.setSizeFull();
        placeGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        placeGrid.addItemClickListener(itemClick -> showEdit(itemClick.getItem()));
        filterTextField = (TextField) buildFilter();
    }

    private Component buildFilter() {
        final TextField filter = new TextField();
        filter.addValueChangeListener(event -> {
            Collection<Place> transactions = placeService.getAll()
                    .stream().filter(place ->
                            passesFilter(place.getName()))
                    .collect(Collectors.toList());
            placeGrid.setDataProvider(DataProvider.ofCollection(transactions));
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
                    Notification.show(placeGrid.getSelectedItems().iterator().next().getId().toString());
                    placeService.remove(placeGrid.getSelectedItems());
                    getUI().getNavigator().navigateTo(NAME);
                });
        addComponent(remove);
        return remove;
    }


    private void showEdit(Place place) {
        if (hasRole(RoleName.OPERATOR.name()) || hasRole(RoleName.ADMIN.name())) {
            Window editWindow = new Window("Edit place");
            editWindow.setContent(addPlaceView);
            addPlaceView.setPlace(place);
            if(getUI().getWindows().size() == 0)
                getUI().addWindow(editWindow);
            editWindow.center();
            editWindow.setHeight("70%");
            editWindow.addCloseListener(closeEvent -> {
                Collection<Place> categoryCollection = placeService.getAll();
                placeGrid.setDataProvider(DataProvider.ofCollection(categoryCollection));
            });
        }
    }
}
