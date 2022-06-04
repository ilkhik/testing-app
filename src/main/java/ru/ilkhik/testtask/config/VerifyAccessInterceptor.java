package ru.ilkhik.testtask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.ilkhik.testtask.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class VerifyAccessInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            userService.getUserByLogin(auth.getName()).ifPresent(user -> {
                UserDetails userDetails = userDetailsService.loadUserByUsername(user.getLogin());
                Authentication newAuth = new UsernamePasswordAuthenticationToken(userDetails, user.getPasswordHash(),
                        userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newAuth);
            });
        }

        return true;
    }
}
