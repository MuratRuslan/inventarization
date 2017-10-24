package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kg.ksucta.kgfi.inventarization.domain.Item;
import kg.ksucta.kgfi.inventarization.domain.RoleName;
import kg.ksucta.kgfi.inventarization.service.ExportToDocumentService;
import kg.ksucta.kgfi.inventarization.service.ItemService;
import kg.ksucta.kgfi.inventarization.service.impl.ExportToCSVDocument;
import kg.ksucta.kgfi.inventarization.service.impl.ExportToPDFDocument;
import kg.ksucta.kgfi.inventarization.service.impl.ExportToXSLDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static kg.ksucta.kgfi.inventarization.utils.SecurityUtils.hasRole;

/**
 * Created by samsung on 10.05.2017.
 */
@SpringView(name = SearchItemView.NAME)
public class SearchItemView extends VerticalLayout implements View {

    public final static String NAME = "";
    private TextField filterTextField;
    private Collection<Item> itemCollection;
    private Grid<Item> items;
    private NativeSelect<ExportToDocumentService> documentSelect;
    private Button download = new Button("download");
    private ListDataProvider<Item> dataProvider;
    private FileDownloader fileDownloader;

    @Autowired
    private AddItemView addItemView;

    @Autowired
    private ItemService itemService;

    @Autowired private ExportToCSVDocument exportToCSVDocument;
    @Autowired private ExportToPDFDocument exportToPDFDocument;
    @Autowired private ExportToXSLDocument exportToXSLDocument;


    @PostConstruct
    private void init() {
        initComponents();
        addComponents(
                new HorizontalLayout(filterTextField, documentSelect, download),
                items);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        itemCollection = itemService.getAll();
        dataProvider = DataProvider.ofCollection(itemCollection);
        items.setDataProvider(dataProvider);
        addDownloadListener();
        if (hasRole(RoleName.ADMIN.name()) || hasRole(RoleName.OPERATOR.name()))
            buildRemoveButton();
    }

    private void initComponents() {
        items = new Grid<>();
        TextField itemName = new TextField("Name");
        items.addColumn(Item::getName).setCaption("Item name").setEditorComponent(itemName, Item::setName);
        items.addColumn(Item::getArticleNumber).setCaption("Article number");
        items.addColumn(Item::getSecondArtikelNumber).setCaption("Second Art number");
        items.addColumn(Item::getCategory).setCaption("Category");
        items.addColumn(Item::getPlace).setCaption("Place");
        items.addColumn(Item::getCost).setCaption("Cost EURO");
        items.addColumn(Item::getCostSom).setCaption("Cost SOM");
        items.addColumn(Item::getAuthor).setCaption("Author");
        items.addColumn(Item::getIsbn).setCaption("ISBN");
        items.addColumn(Item::getPurchaseDate).setCaption("Purchase date");
        items.addColumn(Item::getRegistrationDate).setCaption("Registration date");
        items.addColumn(Item::getProject).setCaption("Project");
        items.addColumn(Item::getDescription).setCaption("Description");
        items.setSizeFull();
        items.setSelectionMode(Grid.SelectionMode.SINGLE);
        items.addItemClickListener(itemClick -> showItemEdit(itemClick.getItem()));
        items.setSelectionMode(Grid.SelectionMode.SINGLE);
        filterTextField = (TextField) buildFilter();
        documentSelect = new NativeSelect<>();
        documentSelect.setItems(new ArrayList<>(
                Arrays.asList(exportToCSVDocument, exportToPDFDocument, exportToXSLDocument)));
        documentSelect.setSelectedItem(exportToPDFDocument);
        documentSelect.setEmptySelectionAllowed(false);
    }

    private Component buildFilter() {
        final TextField filter = new TextField();
        filter.addValueChangeListener(event -> {
            Collection<Item> transactions = itemCollection
                    .stream().filter(item -> passesFilter(item.getName())
                            || passesFilter(item.getArticleNumber())
                            || passesFilter(item.getCategory())
                            || passesFilter(item.getProject())
                            || passesFilter(item.getAuthor())
                            || passesFilter(item.getPlace())).collect(Collectors.toList());

            dataProvider = DataProvider.ofCollection(transactions);
            items.setDataProvider(dataProvider);
            fileDownloader.setFileDownloadResource(
                    documentSelect.getValue().export(dataProvider.getItems()));
        });

        filter.setPlaceholder("Filter");
        filter.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        return filter;
    }

    private <T> boolean passesFilter(T subject) {
        if (subject == null) {
            return false;
        }
        return subject.toString().trim().toLowerCase()
                .contains(filterTextField.getValue().toLowerCase());
    }

    private Component buildRemoveButton() {
        Button remove = new Button("remove",
                clickEvent -> {
                    Notification.show(items.getSelectedItems().iterator().next().getId().toString());
                    itemService.remove(items.getSelectedItems());
                    getUI().getNavigator().navigateTo(NAME);
                });
        addComponent(remove);
        return remove;
    }

    private void addDownloadListener() {
        fileDownloader = new FileDownloader(documentSelect.getValue().export(dataProvider.getItems()));
        fileDownloader.extend(download);
        documentSelect.addValueChangeListener(e ->
                fileDownloader.setFileDownloadResource(
                        documentSelect.getValue().export(dataProvider.getItems())));
    }


    private void showItemEdit(Item item) {
        if (hasRole(RoleName.OPERATOR.name()) || hasRole(RoleName.ADMIN.name())) {
            if (getUI().getWindows().isEmpty()) {
                Window editWindow = new Window("Edit item");
                editWindow.setContent(addItemView);
                getUI().addWindow(editWindow);
                editWindow.center();
                editWindow.setHeight("70%");
                editWindow.addCloseListener(closeEvent -> {
                    itemCollection = itemService.getAll();
                    items.setDataProvider(DataProvider.ofCollection(itemCollection));
                });
            }
            addItemView.setItem(item);
        }
    }
}
