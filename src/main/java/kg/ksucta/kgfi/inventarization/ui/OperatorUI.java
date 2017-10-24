package kg.ksucta.kgfi.inventarization.ui;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kg.ksucta.kgfi.inventarization.utils.VaadinComponentHelper;
import kg.ksucta.kgfi.inventarization.view.AddItemView;
import kg.ksucta.kgfi.inventarization.view.SearchItemView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by samsung on 12.05.2017.
 */
@SpringUI(path = "/operator")
public class OperatorUI extends UI {

    @Autowired private SpringViewProvider springViewProvider;
    private Panel springViewDisplay;
    private Navigator navigator;
    private Button logout;

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        springViewDisplay = new Panel();
        springViewDisplay.setSizeFull();
        navigator = new Navigator(this, springViewDisplay);
        navigator.addProvider(springViewProvider);
        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(createNavigationButton("Item Registration", AddItemView.NAME));
        navigationBar.addComponent(createNavigationButton("Search item", SearchItemView.NAME));
        root.addComponents(navigationBar, springViewDisplay);
        root.setExpandRatio(springViewDisplay, 1.0f);

        setContent(root);
        logout = VaadinComponentHelper.buildLogoutButton(this);
    }

    private Button createNavigationButton(String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);

        button.addClickListener(event -> navigator.navigateTo(viewName) );
        return button;
    }

}
