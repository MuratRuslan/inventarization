package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by samsung on 12.05.2017.
 */
@SpringView(name = "")
public class DefaultView extends VerticalLayout implements View {
    public static final String NAME = "";

    public DefaultView() {
        addComponent(new Label("DefaultView"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
