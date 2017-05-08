package kg.ksucta.kgfi.inventarization.view

import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.spring.annotation.UIScope
import com.vaadin.ui.Label
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme
import org.springframework.stereotype.Component

/**
 * Created by murat on 5/6/17.
 */
@Component // No SpringView annotation because this view can not be navigated to
@UIScope
class ErrorView extends VerticalLayout implements View {

    private Label errorLabel;

    public ErrorView() {
        setMargin(true);
        errorLabel = new Label();
        errorLabel.addStyleName(ValoTheme.LABEL_FAILURE);
        errorLabel.setSizeUndefined();
        addComponent(errorLabel);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        errorLabel.setValue(String.format("No such view: %s", event.getViewName()));
    }
}
