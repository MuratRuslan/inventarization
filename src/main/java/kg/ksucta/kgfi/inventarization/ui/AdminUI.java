package kg.ksucta.kgfi.inventarization.ui;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import kg.ksucta.kgfi.inventarization.utils.SecurityUtils;
import kg.ksucta.kgfi.inventarization.utils.VaadinComponentHelper;
import kg.ksucta.kgfi.inventarization.view.*;
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
    private Button logout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        springViewDisplay = new Panel();
        springViewDisplay.setSizeFull();
        navigator = new Navigator(this, springViewDisplay);
        navigator.addProvider(springViewProvider);
        menuBar = new MenuBar();

        MenuBar.MenuItem add = menuBar.addItem("Add ...", null);
        add.addItem("Category", menuItem ->  navigator.navigateTo(AddCategoryView.NAME));
        add.addItem("Place", menuItem -> navigator.navigateTo(AddPlaceView.NAME));
        add.addItem("Item", menuItem -> navigator.navigateTo(AddItemView.NAME));
        add.addItem("User", menuItem -> navigator.navigateTo(AddUserView.NAME));
        add.addItem("Project", menuItem -> navigator.navigateTo(AddProjectView.NAME));
        MenuBar.MenuItem search = menuBar.addItem("Search", null);
        search.addItem("Item", menuItem -> navigator.navigateTo(SearchItemView.NAME));
        search.addItem("Category", menuItem -> navigator.navigateTo(SearchCategoryView.NAME));
        search.addItem("User", menuItem -> navigator.navigateTo(SearchUserView.NAME));
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponents(menuBar, springViewDisplay);
        setContent(verticalLayout);
        logout = VaadinComponentHelper.buildLogoutButton(this);
    }

}
