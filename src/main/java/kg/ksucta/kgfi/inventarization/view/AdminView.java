package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.security.access.annotation.Secured;

import javax.annotation.PostConstruct;

/**
 * Created by samsung on 12.05.2017.
 */
@SpringView(name = AdminView.NAME)
public class AdminView extends VerticalLayout implements View {
    public static final String NAME = "AdminView";
    private MenuBar menuBar;


    @PostConstruct
    private void init() {
        menuBar = new MenuBar();

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    public void showUserRegistration(){

    }

    public void showRegistration(){

    }



}