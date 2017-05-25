package kg.ksucta.kgfi.inventarization.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;


/**
 * Created by samsung on 12.05.2017.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/login/**").anonymous()
                .antMatchers("/vaadinServlet/UIDL/**").permitAll()
                .antMatchers("/vaadinServlet/HEARTBEAT/**", "/VAADIN/**", "/PUSH/**", "/UIDL/**").permitAll()
                .anyRequest().authenticated();
        http.exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));

        http.httpBasic().disable();
        http.formLogin().disable();
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

    /*@Bean(name = VaadinSharedSecurityConfiguration.VAADIN_AUTHENTICATION_SUCCESS_HANDLER_BEAN)
    protected VaadinAuthenticationSuccessHandler vaadinAuthenticationSuccessHandler(HttpService httpService, VaadinRedirectStrategy vaadinRedirectStrategy) {
        return new VaadinUrlAuthenticationSuccessHandler(httpService, vaadinRedirectStrategy, "/");
    }*/

}