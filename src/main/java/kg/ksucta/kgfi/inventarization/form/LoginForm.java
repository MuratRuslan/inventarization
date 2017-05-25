package kg.ksucta.kgfi.inventarization.form;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;

public class LoginForm extends VerticalLayout {

    public LoginForm(LoginCallback callback) {
        setMargin(true);
        setSpacing(true);

        TextField username = new TextField("username");
        addComponent(username);

        PasswordField password = new PasswordField("password");
        addComponent(password);

        Button login = new Button("Login", evt -> {
            String pword = password.getValue();
            password.setValue("");
            if (!callback.login(username.getValue(), pword)) {
                Notification.show("Login failed");
                username.focus();
            }
        });
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        addComponent(login);
    }

    @FunctionalInterface
    public interface LoginCallback {
        boolean login(String username, String password);
    }
}