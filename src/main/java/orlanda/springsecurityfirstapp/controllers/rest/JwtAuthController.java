package orlanda.springsecurityfirstapp.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import orlanda.springsecurityfirstapp.JwtUtil;
import orlanda.springsecurityfirstapp.models.Person;
import orlanda.springsecurityfirstapp.models.rest.AuthenticationDTO;
import orlanda.springsecurityfirstapp.models.rest.PersonDTO;
import orlanda.springsecurityfirstapp.services.RegistrationService;

@RequestMapping("/rest")
@RestController
public class JwtAuthController {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/login")
    public String loginPage(AuthenticationDTO authenticationDTO) {
//        Authentication authentication = ...

        return "OKS!";
    }

    @PostMapping("/register")
    public String performRegistration(@RequestBody PersonDTO personDTO) {
        Person person = convertToPerson(personDTO);
        registrationService.register(person);

        return jwtUtil.generateToken(person.getUsername());

//        return "MY VAS ZAREGALI! OK";
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
