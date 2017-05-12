package kg.ksucta.kgfi.inventarization.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by samsung on 12.05.2017.
 */
public enum RoleName implements GrantedAuthority {
    ROLE_ADMIN, ROLE_USER, ROLE_OPERATOR;

    @Override
    public String getAuthority() {
        return this.name();
    }
}