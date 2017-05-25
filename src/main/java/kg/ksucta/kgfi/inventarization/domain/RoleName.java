package kg.ksucta.kgfi.inventarization.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by samsung on 12.05.2017.
 */
public enum RoleName implements GrantedAuthority {
    ADMIN, USER, OPERATOR;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}