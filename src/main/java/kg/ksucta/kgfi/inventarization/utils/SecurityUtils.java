package kg.ksucta.kgfi.inventarization.utils;

import kg.ksucta.kgfi.inventarization.domain.RoleName;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;


public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    public static boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().contains(RoleName.valueOf(role));
    }

    public static void logout() {
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
    }
}
