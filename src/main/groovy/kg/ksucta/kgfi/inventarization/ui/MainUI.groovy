package kg.ksucta.kgfi.inventarization.ui

import com.vaadin.annotations.Theme
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.UI
import kg.ksucta.kgfi.inventarization.view.LoginView
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by murat on 4/28/17.
 */
@SpringUI(path="/")
@Theme("valo")
class MainUI extends UI{

    @Autowired
    LoginView loginView;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(loginView)
    }


}
