package com.kpi.payments.controller;

import com.kpi.payments.entity.User;
import com.kpi.payments.service.ServiceFactory;
import com.kpi.payments.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = ServiceFactory.getInstance().createUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = userService.login(login, password);

        if (!Objects.nonNull(user)) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/user/account");
        } else {
            req.setAttribute("error", "User login failed");
            resp.sendRedirect("/login");
        }
    }
}
