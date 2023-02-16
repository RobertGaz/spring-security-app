package orlanda.springsecurityfirstapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import orlanda.springsecurityfirstapp.models.Person;
import orlanda.springsecurityfirstapp.services.RegistrationService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/register")
    public String performRegistration(@ModelAttribute("person") Person person) {
        registrationService.register(person);

        return "redirect:/auth/login";
    }
}
