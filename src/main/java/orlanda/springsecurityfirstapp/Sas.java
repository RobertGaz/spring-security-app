package orlanda.springsecurityfirstapp;

import org.springframework.security.access.prepost.PreAuthorize;

public class Sas {

    @PreAuthorize("hasAnyRole('USER')")
    public void sas() {
        System.out.println("SAS");
    }

}
