package orlanda.springsecurityfirstapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import orlanda.springsecurityfirstapp.services.PersonDetailsService;

import java.util.Collections;

// uncomment to turn on
//@Component
public class AuthProvider implements AuthenticationProvider {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public AuthProvider(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("*** AuthProvider.authenticate()");
        System.out.println(authentication);

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails personDetails = personDetailsService.loadUserByUsername(username);


        System.out.println("Ty DAL MNE: " + password);
        System.out.println("PRAVILNY: " + personDetails.getPassword());

        if (!password.equals(personDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password!");
        }

        return new UsernamePasswordAuthenticationToken(personDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
