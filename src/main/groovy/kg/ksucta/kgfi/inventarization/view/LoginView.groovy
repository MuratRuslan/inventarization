package kg.ksucta.kgfi.inventarization.view

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.SpringComponent
import com.vaadin.spring.annotation.SpringView
import com.vaadin.spring.annotation.UIScope
import com.vaadin.ui.Button
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

/**
 * Created by dronk_000 on 29.04.2017.
 */
@SpringComponent
@UIScope
class LoginView extends VerticalLayout implements View {
    final static String NAME = "LoginView"

    @PostConstruct
    void init() {
        addComponents(new Label("bla blab l"), new Button("ok"))
    }

    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
