package orlanda.springsecurityapp.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import orlanda.springsecurityapp.JwtUtil;
import orlanda.springsecurityapp.security.PersonDetails;
import orlanda.springsecurityapp.services.PersonDetailsService;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    private PersonDetailsService personDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);

            UserDetails userDetails;

            try {
                System.out.println("trying to work with jwt " + jwt);
                String username = jwtUtil.verifyTokenAndRetrieveUsernameClaim(jwt);
                System.out.println("retrieved username from jwt: " + username);
                userDetails = personDetailsService.loadUserByUsername(username);
            } catch (Exception e) {
                System.out.println("catched " + e);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "CATASTROPHIC");
                return;
            }

            UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.authenticated(
                    userDetails,
                    userDetails.getPassword(),
                    userDetails.getAuthorities()
            );

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }

        filterChain.doFilter(request, response);

    }
}
