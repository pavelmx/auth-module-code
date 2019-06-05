package com.innowise.AuthorizationServerApplication.security;//package com.innowise.authmodule.security;


import com.innowise.AuthorizationServerApplication.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class MySimpleUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();
        if (user.getEmployeeId() != null) {
            Cookie employee_id = new Cookie("EMPLOYEE_ID", user.getEmployeeId().toString());
            response.addCookie(employee_id);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}