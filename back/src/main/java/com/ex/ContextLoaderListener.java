package com.ex;

import com.ex.connections.DBConnector;
import com.ex.connections.GeneralDBConnector;
import com.ex.daos.MongoDao;
import com.ex.services.DataService;
import com.ex.services.Service;
import com.ex.utils.Logging;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

public class ContextLoaderListener implements ServletContextListener {
    /**
     * Called when context is initialized
     * @param servletContextEvent Servlet context
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Logging.getLogger().info("Servlet Context initializing.");
        System.out.println("Initializing servlet context");
        ServletContext context = servletContextEvent.getServletContext();
        String url = context.getInitParameter("DB_URL");
        String username = context.getInitParameter("DB_USERNAME");
        String password = context.getInitParameter("DB_PASSWORD");
        String driverName = context.getInitParameter("DB_DRIVER");

        try {
            DBConnector connector = new GeneralDBConnector(username, password, url, driverName);
            MongoDao dao = new MongoDao(connector);
            Service service = new DataService(dao);
            context.setAttribute("dataService", service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when context is destroyed
     * @param servletContextEvent Servlet context
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Logging.getLogger().info("Servlet Context destroyed.");
    }
}
