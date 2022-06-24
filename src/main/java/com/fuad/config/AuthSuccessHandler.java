package com.fuad.config;

import com.fuad.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        HttpSession session = request.getSession();
//        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User authUser = (User) authentication.getPrincipal();

        session.setAttribute("user", authUser);
        session.setAttribute("username", authUser.getUsername());
        session.setAttribute("authorities", authUser.getAuthorities());

        response.setStatus(HttpServletResponse.SC_OK);


//        if(authUser.getRole().toString().contains("ADMIN")) {
//            response.sendRedirect("/user/maintain");
//        } else {
//            response.sendRedirect("/user/show/"+authUser.getId());
//        }

        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        response.sendRedirect(savedRequest == null ? "/home" : savedRequest.getRedirectUrl());
    }
}
