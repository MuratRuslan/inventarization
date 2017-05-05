package kg.ksucta.kgfi.inventarization.view

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.server.FileDownloader
import com.vaadin.spring.annotation.SpringView
import com.vaadin.ui.Button
import com.vaadin.ui.Label
import com.vaadin.ui.LoginForm
import com.vaadin.ui.VerticalLayout

import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

/**
 * Created by dronk_000 on 29.04.2017.
 */
@SpringView(name = LoginView.NAME)
class LoginView extends LoginForm implements View {
    final static String NAME = "LoginView"


    @Override
    void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
