package kg.ksucta.kgfi.inventarization.utils;

import com.vaadin.ui.*;

/**
 * Created by murat on 10/6/17.
 */
public class VaadinComponentHelper {

    public static Button buildLogoutButton(UI ui) {
        Button button = new Button("logout", e -> {
            SecurityUtils.logout();
            ui.getPage().reload();
        });
        VerticalLayout content = (VerticalLayout) (ui.getContent());
        content.addComponent(button, 0);
        content.setComponentAlignment(button, Alignment.TOP_RIGHT);
        return button;
    }
}
