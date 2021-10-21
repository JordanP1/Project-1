package com.ex.controllers;

import com.ex.models.Reimbursement;
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

public class ReimbursementController extends HttpServlet {
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
        System.out.println("Reimbursement Service called");
        super.service(req, resp);
    }

    /**
     * Servlet destroyed
     */
    @Override
    public void destroy() {
        System.out.println("Reimbursement Destroyed");
    }

    /**
     * Called when Servlet is initialized
     * @param config Servlet configuration
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Reimbursement Init");
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
        System.out.println("GET request");

        if (idStr != null) {
            Logging.getLogger().info("GET Id: " + idStr);
            int id = Integer.parseInt(idStr);
            Reimbursement reimbursement = service.getReimbursement(id);
            String json = mapper.writeValueAsString(reimbursement);
            resp.setStatus((200));
            resp.setContentType("application/json");
            resp.getWriter().write(json);
            return;
        }

        Logging.getLogger().info("Requesting all reimbursements.");
        System.out.println("Requesting all reimbursements.");

        List<Reimbursement> reimbursements = service.getAllReimbursements();
        String json = mapper.writeValueAsString(reimbursements);
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
        Reimbursement incoming = mapper.readValue(req.getInputStream(), Reimbursement.class);

        int id;

        if (incoming.getSubmitterId() != -2) {
            System.out.println("Saving reimbursements" + incoming);
            Logging.getLogger().info("Saving reimbursements " + incoming);
            id = service.saveReimbursement(incoming);
        } else {
            System.out.println("Updating reimbursements" + incoming);
            Logging.getLogger().info("Updating reimbursements " + incoming);
            id = service.updateReimbursement(incoming);
        }

        resp.setStatus(201); //Created
        resp.setHeader("Location", String.valueOf(URI.create("http://localhost:8080/back/api/reimbursements?id=" + id)));
    }
}
