package com.alternate.sample.servlets;

import com.alternate.sample.services.GreetingService;
import com.alternate.sample.services.GreetingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private GreetingService greetingService = new GreetingServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String message = greetingService.getGreetingText(request.getParameter("username"));
        request.setAttribute("message", message);
        try {
            request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
