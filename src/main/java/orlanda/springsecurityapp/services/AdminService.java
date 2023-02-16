package orlanda.springsecurityapp.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @PreAuthorize("hasRole('ADMIN')")
    public void doSomeAdminVipStaff() {
        System.out.println("DELAEM VIP DELA");
    }
}
