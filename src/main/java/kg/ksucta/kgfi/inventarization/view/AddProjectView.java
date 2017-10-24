package kg.ksucta.kgfi.inventarization.view;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kg.ksucta.kgfi.inventarization.domain.Project;
import kg.ksucta.kgfi.inventarization.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Created by murat on 10/4/17.
 */
@SpringView(name = AddProjectView.NAME)
public class AddProjectView extends VerticalLayout implements View {

    public final static String NAME = "Add Project";
    private Label header;
    private TextField name;
    private Button save;
    private Binder<Project> binder;

    @Autowired
    private ProjectService projectService;

    @PostConstruct
    void init() {
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        header = new Label("Create project");
        name = new TextField("Name");
        save = new Button("Save");
        save.setEnabled(false);
        initBinder();

        save.addClickListener(event -> {
            saveProject();
            Notification.show("Success");
        });
        binder.addStatusChangeListener(
                event -> save.setEnabled(binder.isValid()));

        addComponents(header, name, save);

    }

    public void setProject(Project project) {
        binder.readBean(project);
        binder.setBean(project);
    }

    private void initBinder() {
        binder = new Binder<>();
        binder.forField(name)
                .asRequired("Name may not be empty")
                .bind(Project::getName, Project::setName);
        binder.addStatusChangeListener(
                event -> save.setEnabled(binder.isValid()));
        binder.setBean(new Project());
    }

    @Transactional
    private void saveProject() {
        projectService.save(binder.getBean());
        getUI().getNavigator().navigateTo(this.NAME);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
