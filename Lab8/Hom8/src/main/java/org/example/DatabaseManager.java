package org.example;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    private static final DatabaseManager instance = new DatabaseManager();
    private final DataSource dataSource;

    private DatabaseManager() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306/book_managing");
            cpds.setUser("root");
            cpds.setPassword("Bazadedate1");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        this.dataSource = cpds;
    }

    public static DatabaseManager getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
