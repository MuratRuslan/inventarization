package kg.ksucta.kgfi.inventarization.ui;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import kg.ksucta.kgfi.inventarization.view.AddCategoryView;
import kg.ksucta.kgfi.inventarization.view.AddPlaceView;
import kg.ksucta.kgfi.inventarization.view.SearchItemView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by murat on 5/24/17.
 */
@SpringUI(path = "/admin")

public class AdminUI extends UI {
    @Autowired
    private SpringViewProvider springViewProvider;
    private Panel springViewDisplay;
    private Navigator navigator;
    private MenuBar menuBar;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        springViewDisplay = new Panel();
        springViewDisplay.setSizeFull();
        menuBar = new MenuBar();
        MenuBar.MenuItem add = menuBar.addItem("add", null);
        add.addItem("add Category", menuItem ->  getNavigator().navigateTo(AddCategoryView.NAME));
        add.addItem("add Place", menuItem -> getNavigator().navigateTo(AddPlaceView.NAME));
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponents(menuBar, new Label("hello"), springViewDisplay);
        setContent(verticalLayout);
        navigator = new Navigator(this, springViewDisplay);
        navigator.addProvider(springViewProvider);
        setNavigator(navigator);
    }
}