package kg.ksucta.kgfi.inventarization.service.impl;

import kg.ksucta.kgfi.inventarization.domain.Role;
import kg.ksucta.kgfi.inventarization.repository.RoleRepository;
import kg.ksucta.kgfi.inventarization.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by murat on 5/25/17.
 */
@Service
public class DefaultRoleService implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void remove(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void remove(Collection<Role> roles) {
        roleRepository.delete(roles);
    }
}
