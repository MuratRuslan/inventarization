package kg.ksucta.kgfi.inventarization.service.impl

import kg.ksucta.kgfi.inventarization.domain.Person
import kg.ksucta.kgfi.inventarization.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by murat on 5/6/17.
 */
@Service("authService")
class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private PersonRepository userInfoRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person user = userInfoRepository.findByLogin(login)
        Set<GrantedAuthority> grantedAuthorities = user.roles.collect{new SimpleGrantedAuthority(it.name as String)}
        new org.springframework.security.core.userdetails.User(user.login, user.password, grantedAuthorities);
    }
}
