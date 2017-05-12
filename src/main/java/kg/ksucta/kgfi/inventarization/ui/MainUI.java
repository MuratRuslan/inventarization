package kg.ksucta.kgfi.inventarization.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kg.ksucta.kgfi.inventarization.view.AdminView;
import kg.ksucta.kgfi.inventarization.view.DefaultView;
import kg.ksucta.kgfi.inventarization.view.RegistrationItemView;
import kg.ksucta.kgfi.inventarization.view.SearchItemView;

/**
 * Created by samsung on 12.05.2017.
 */
@SpringUI
@Theme("valo")
@SpringViewDisplay
public class MainUI extends UI implements ViewDisplay {
    private Panel springViewDisplay;

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        setContent(root);

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(createNavigationButton("DefaultView", DefaultView.NAME));
        navigationBar.addComponent(createNavigationButton("AdminView", AdminView.NAME));
        navigationBar.addComponent(createNavigationButton("Item Registration", RegistrationItemView.NAME));
        navigationBar.addComponent(createNavigationButton("Search item", SearchItemView.NAME));
        //navigationBar.addComponent(createNavigationButton('User Registration', RegistrationUserView.NAME))
        root.addComponent(navigationBar);

        springViewDisplay = new Panel();
        springViewDisplay.setSizeFull();
        root.addComponent(springViewDisplay);
        root.setExpandRatio(springViewDisplay, 1.0f);

    }

    private Button createNavigationButton(String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);

        button.addClickListener(event -> getNavigator().navigateTo(viewName) );
        return button;
    }

    @Override
    public void showView(View view) {
        springViewDisplay.setContent((Component) view);
    }
}
