package com.ex.connections;

import com.ex.utils.Logging;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GeneralDBConnector implements DBConnector {
    private String username;
    private String password;
    private String url;
    private  String driverName;

    private static Properties properties = new Properties();

    /**
     * GeneralDBConnector to store database credentials.
     * @param username Login Username
     * @param password Login password
     * @param url Url to database
     * @param driverName Database driver
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    public GeneralDBConnector(String username, String password, String url, String driverName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.username = username;
        this.password = password;
        this.url = url;
        this.driverName = driverName;
        DriverManager.registerDriver((Driver)Class.forName(this.driverName).newInstance());
        Logging.getLogger().info("GeneralDBConnector initialized.");
    }

    /**
     * Get a new database connection.
     * @param username
     * @param password
     * @param url
     * @return
     * @throws SQLException
     */
    @Override
    public Connection newConnection(String username, String password, String url) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Get a new database connection using the db.properties credentials.
     * @return
     * @throws SQLException
     */
    @Override
    public Connection newConnection() throws SQLException {
        return newConnection(username, password, url);
    }
}
