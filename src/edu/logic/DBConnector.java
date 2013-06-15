package edu.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <code>DBConnector</code> Class allow to establish a quick connection
 * with Database Engine
 * 
 * @author david
 */
public class DBConnector {
    
    private Connection conn;
    
    public Connection getConnection() throws SQLException {
        
        Properties connectionProps = new Properties();
        connectionProps.put("user", Setting.DB_USER);
        connectionProps.put("password", Setting.DB_PASS);

        conn = DriverManager.getConnection(
                   "jdbc:" + Setting.DBMS + "://" +
                   Setting.DB_SERVER_NAME +
                   ":" + Setting.PORT_NUMBER + "/" + Setting.DB_NAME,
                   connectionProps);
        System.out.println("Connected successfully to database");
        return conn;
    }
}
