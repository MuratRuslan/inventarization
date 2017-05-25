package kg.ksucta.kgfi.inventarization.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import kg.ksucta.kgfi.inventarization.form.LoginForm;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by murat on 5/24/17.
 */
@SpringUI(path = "/login")
public class LoginUI extends UI {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        LoginForm loginForm = new LoginForm(this::login);
        VerticalLayout layout = new VerticalLayout(loginForm);
        layout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    private boolean login(String username, String password) {
        try {
            Authentication token = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            VaadinService.reinitializeSession(VaadinService.getCurrentRequest());
            SecurityContextHolder.getContext().setAuthentication(token);
            getPage().setLocation("/");
            return true;
        } catch (AuthenticationException ex) {
            return false;
        }
    }
}
