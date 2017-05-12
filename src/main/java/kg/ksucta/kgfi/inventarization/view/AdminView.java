package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.security.access.annotation.Secured;

/**
 * Created by samsung on 12.05.2017.
 */
@SpringView(name = AdminView.NAME)
public class AdminView extends VerticalLayout implements View {
    public static final String NAME = "AdminView";

    public AdminView() {
        addComponent(new Label("AdminView"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}