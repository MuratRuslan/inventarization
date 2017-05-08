package kg.ksucta.kgfi.inventarization.ui

import com.vaadin.annotations.Theme
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewDisplay
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.spring.annotation.SpringViewDisplay
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme
import kg.ksucta.kgfi.inventarization.view.AdminView
import kg.ksucta.kgfi.inventarization.view.DefaultView
import kg.ksucta.kgfi.inventarization.view.LoginView
import kg.ksucta.kgfi.inventarization.view.RegistrationItemView

/**
 * Created by murat on 4/28/17.
 */
@Theme("valo")
@SpringUI(path='/')
@SpringViewDisplay
class MainUI extends UI implements ViewDisplay {

    private Panel springViewDisplay;

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        setContent(root);

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(createNavigationButton("DefaultView",
                DefaultView.NAME));
        navigationBar.addComponent(createNavigationButton("LoginView", LoginView.NAME));
        navigationBar.addComponent(createNavigationButton("AdminView", AdminView.NAME));
        navigationBar.addComponent(createNavigationButton("Item Registration", RegistrationItemView.NAME))
        root.addComponent(navigationBar);

        springViewDisplay = new Panel();
        springViewDisplay.setSizeFull();
        root.addComponent(springViewDisplay);
        root.setExpandRatio(springViewDisplay, 1.0f);

    }

    private Button createNavigationButton(String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        // If you didn't choose Java 8 when creating the project, convert this
        // to an anonymous listener class
        button.addClickListener({ event -> getUI().getNavigator().navigateTo(viewName) });
        return button;
    }

    @Override
    public void showView(View view) {
        springViewDisplay.setContent((Component) view);
    }
}