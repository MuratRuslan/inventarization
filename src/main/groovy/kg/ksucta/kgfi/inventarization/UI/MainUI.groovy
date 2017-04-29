package kg.ksucta.kgfi.inventarization.ui

import com.vaadin.annotations.Theme
import com.vaadin.server.VaadinRequest
import com.vaadin.spring.annotation.SpringUI
import com.vaadin.ui.Label
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout

/**
 * Created by murat on 4/28/17.
 */
@SpringUI(path="/")
@Theme("valo")
class MainUI extends UI{
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout()
        layout.addComponent(new Label("hello"))
        setContent(layout)
    }
}
