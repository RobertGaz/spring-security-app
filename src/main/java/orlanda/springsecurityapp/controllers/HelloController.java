package orlanda.springsecurityapp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import orlanda.springsecurityapp.Sas;
import orlanda.springsecurityapp.security.PersonDetails;
import orlanda.springsecurityapp.services.AdminService;

@Controller
public class HelloController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("HELLO CONTROLLER");
        return "hello";
    }

    @GetMapping("/user")
    @ResponseBody
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        System.out.println(personDetails.getPerson());
        return personDetails.getUsername();
    }

    @GetMapping("/admin_page")
    public String adminPage() {
        adminService.doSomeAdminVipStaff();
        return "admin";
    }

    @GetMapping("/user_page")
    public String userPage() {
        Sas sas = new Sas();
        sas.sas();
        return "hello";
    }

}
