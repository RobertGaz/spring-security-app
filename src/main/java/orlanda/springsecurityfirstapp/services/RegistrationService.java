package orlanda.springsecurityfirstapp.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import orlanda.springsecurityfirstapp.models.Person;
import orlanda.springsecurityfirstapp.repositories.PeopleRepository;

@Service
public class RegistrationService {

    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void register(Person person) {
        String encodedPassword = passwordEncoder.encode(person.getPassword());
        person.setPassword(encodedPassword);

        person.setRole("ROLE_USER");
        peopleRepository.save(person);
    }

}
