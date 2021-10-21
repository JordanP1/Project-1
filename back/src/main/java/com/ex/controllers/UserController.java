package com.ex.controllers;

import com.ex.models.User;
import com.ex.services.Service;
import com.ex.utils.Logging;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;

public class UserController extends HttpServlet {
    private ObjectMapper mapper;
    private Service service;

    /**
     * Servlet service
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("User Service called");
        super.service(req, resp);
    }

    /**
     * Servlet destroyed
     */
    @Override
    public void destroy() {
        System.out.println("User Service Destroyed");
    }

    /**
     * Called when Servlet is initialized
     * @param config Servlet configuration
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("User Init");
        ServletContext context = config.getServletContext();
        mapper = new ObjectMapper();
        service = (Service)context.getAttribute("dataService");
    }

    /**
     * GET request
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logging.getLogger().info("GET request");
        String idStr = req.getParameter("id");

        if (idStr != null) {
            Logging.getLogger().info("GET Id: " + idStr);
            int id = Integer.parseInt(idStr);
            User user = service.getUser(id);
            String json = mapper.writeValueAsString(user);
            resp.setStatus((200));
            resp.setContentType("application/json");
            resp.getWriter().write(json);
            return;
        }

        Logging.getLogger().info("Requesting all users.");

        List<User> users = service.getAllUsers();
        String json = mapper.writeValueAsString(users);
        resp.setStatus((200));
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    /**
     * POST request
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logging.getLogger().info("POST request");
        User incoming = mapper.readValue(req.getInputStream(), User.class);
        System.out.println("Saving user" + incoming);
        Logging.getLogger().info("Saving user " + incoming);
        int id = service.saveUser(incoming);
        resp.setStatus(201); //Created
        resp.setHeader("Location", String.valueOf(URI.create("http://localhost:8080/back/api/users?id=" + id)));
    }
}
