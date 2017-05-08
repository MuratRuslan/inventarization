package kg.ksucta.kgfi.inventarization.view

import com.vaadin.ui.Button
import com.vaadin.ui.DateField
import com.vaadin.ui.Label
import com.vaadin.ui.NativeSelect
import com.vaadin.ui.TextArea
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme

/**
 * Created by murat on 5/8/17.
 */
public class HelperLayout extends VerticalLayout {

    Label addHeader(String name) {
        Label header = new Label(name);
        header.addStyleName(ValoTheme.LABEL_H3);
        addComponent(header);
        return header;
    }

    TextField addTextField(String fieldName) {
        TextField inputField = new TextField(fieldName);
        addComponent(inputField);
        return inputField;
    }

    TextArea addTextArea(String textAreaName, String caption) {
        TextArea textArea = new TextArea(textAreaName);
        textArea.setWordWrap(true);
        textArea.setCaption(caption);
        addComponent(textArea);
        return textArea;
    }


    Button addButton(String action) {
        Button button = new Button(action);
        addComponent(button);
        return button;
    }

    NativeSelect<String> addNativeSelect(String name, Collection<String> collection) {
        NativeSelect<String> nativeSelect = new NativeSelect<>(name, collection);
        nativeSelect.setEmptySelectionAllowed(false);
        addComponent(nativeSelect);
        return nativeSelect;
    }


    DateField addDateField(String dateName) {
        DateField date = new DateField(dateName);
        date.setDateFormat("yyyy/MM/dd");
        date.setLenient(true);
        addComponent(date);
        return date;
    }

}
