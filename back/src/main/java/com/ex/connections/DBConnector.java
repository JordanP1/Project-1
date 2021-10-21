package com.ex.connections;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnector {
    /**
     * Connect to SQL database.
     * @param username
     * @param password
     * @param url
     * @return SQL Connection.
     * @throws SQLException
     */
    Connection newConnection(String username, String password, String url) throws SQLException;
    Connection newConnection() throws SQLException;
}
