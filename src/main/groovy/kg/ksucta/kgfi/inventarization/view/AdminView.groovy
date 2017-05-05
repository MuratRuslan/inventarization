package kg.ksucta.kgfi.inventarization.view

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout

/**
 * Created by dronk_000 on 05.05.2017.
 */
@SpringView(name = AdminView.NAME)
class AdminView extends VerticalLayout implements View {
    static final String NAME = "AdminView"

    public AdminView() {
        addComponent(new Label("AdminView"))
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
