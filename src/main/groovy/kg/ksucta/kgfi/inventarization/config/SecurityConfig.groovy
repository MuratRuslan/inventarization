package kg.ksucta.kgfi.inventarization.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService)
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
            authorizeRequests()
                .antMatchers('/log1n',"/VAADIN/**", "/PUSH/**", "/UIDL/**").permitAll()
                .antMatchers('/*').access("hasRole('ROLE_USER')")
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .usernameParameter('username')
                .passwordParameter('password')
            .and()
                .logout()
                .permitAll()
            .and()
                .csrf().disable()
                .exceptionHandling()
    }


}