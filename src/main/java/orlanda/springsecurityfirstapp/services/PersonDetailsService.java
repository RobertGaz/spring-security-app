package orlanda.springsecurityfirstapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import orlanda.springsecurityfirstapp.models.Person;
import orlanda.springsecurityfirstapp.repositories.PeopleRepository;
import orlanda.springsecurityfirstapp.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> personOptional = peopleRepository.findByUsername(username);
        if (personOptional.isEmpty()) {
            throw new UsernameNotFoundException("No such user in DB!");
        }
        return new PersonDetails(personOptional.get());
    }
}
