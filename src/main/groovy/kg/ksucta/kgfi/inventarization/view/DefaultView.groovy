package kg.ksucta.kgfi.inventarization.view

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout
import org.springframework.security.access.method.P

/**
 * Created by dronk_000 on 05.05.2017.
 */
@SpringView(name = DefaultView.NAME)
class DefaultView extends VerticalLayout implements View {
    static final String NAME = "DefaultView";

    public DefaultView() {
        addComponent(new Label('DefaultView'))
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
