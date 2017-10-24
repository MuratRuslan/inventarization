package kg.ksucta.kgfi.inventarization.service.impl;

import kg.ksucta.kgfi.inventarization.domain.Project;
import kg.ksucta.kgfi.inventarization.repository.ProjectRepository;
import kg.ksucta.kgfi.inventarization.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by murat on 10/1/17.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Collection<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public void save(Project item) {
        projectRepository.save(item);
    }

    @Override
    public void remove(Project item) {
        projectRepository.delete(item);
    }

    @Override
    public void remove(Collection<Project> items) {
        projectRepository.delete(items);
    }
}
