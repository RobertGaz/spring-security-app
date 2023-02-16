package orlanda.springsecurityapp.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import orlanda.springsecurityapp.JwtUtil;
import orlanda.springsecurityapp.models.Person;
import orlanda.springsecurityapp.models.rest.AuthenticationDTO;
import orlanda.springsecurityapp.models.rest.PersonDTO;
import orlanda.springsecurityapp.services.RegistrationService;

import java.util.Map;

@RequestMapping("/rest")
@RestController
public class JwtAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/login")
    public Map<String, String> loginPage(@RequestBody AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(token);
        } catch (BadCredentialsException e) {
            return Map.of("error", e.getMessage());
        }

        return Map.of("token", jwtUtil.generateToken(authenticationDTO.getUsername()));
    }

    @PostMapping("/register")
    public Map<String, String> performRegistration(@RequestBody PersonDTO personDTO) {
        Person person = convertToPerson(personDTO);
        registrationService.register(person);

        return Map.of("token", jwtUtil.generateToken(person.getUsername()));
    }

    public Person convertToPerson(PersonDTO personDTO) {
        // вместо тысячи mapstruct'ов
        Person person = new Person();
        person.setUsername(personDTO.getUsername());
        person.setPassword(personDTO.getPassword());
        person.setYearOfBirth(personDTO.getYearOfBirth());
        return person;
    }

}
