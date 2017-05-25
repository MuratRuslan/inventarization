package kg.ksucta.kgfi.inventarization.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kg.ksucta.kgfi.inventarization.view.RegistrationItemView;
import kg.ksucta.kgfi.inventarization.view.SearchItemView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by samsung on 12.05.2017.
 */
@SpringUI(path = "/")
@Theme("valo")
@SpringViewDisplay
public class MainUI extends UI {

    @Autowired private SpringViewProvider springViewProvider;
    private Panel springViewDisplay;
    private Navigator navigator;

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        setContent(root);


        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(createNavigationButton("Item Registration", RegistrationItemView.NAME));
        navigationBar.addComponent(createNavigationButton("Search item", SearchItemView.NAME));
        root.addComponent(navigationBar);

        springViewDisplay = new Panel();
        springViewDisplay.setSizeFull();
        root.addComponent(springViewDisplay);
        root.setExpandRatio(springViewDisplay, 1.0f);
        navigator = new Navigator(this, springViewDisplay);
        navigator.addProvider(springViewProvider);
        setNavigator(navigator);
    }

    private Button createNavigationButton(String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);

        button.addClickListener(event -> navigator.navigateTo(viewName) );
        return button;
    }

}
