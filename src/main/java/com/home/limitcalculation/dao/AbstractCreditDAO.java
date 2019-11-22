/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.limitcalculation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Denis
 */
public abstract class AbstractCreditDAO implements CreditDAO {

    private final String url;
    private final String user;
    private final String pass;
    private final String driver;

    private volatile Statement statement;
    protected volatile Connection connection;

    public AbstractCreditDAO(String url, String user, String pass, String driver) {
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.driver = driver;
    }

    @Override
    public void openConnection(String dbName) {
        if (dbName.contains("SQL")) {
            getConnection();
        }
    }

    private Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        synchronized (this) {
            if (connection == null) {
                try {
                    Class.forName(driver);
                    connection = DriverManager.getConnection(url, user, pass);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AbstractCreditDAO.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AbstractCreditDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return connection;
    }

    @Override
    public void closeConnect() {
        try {
            if (connection != null) {
                connection.close();
            }

            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbstractCreditDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Statement getStatement() throws SQLException {
        return getConnection().createStatement();
    }

    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }

    public PreparedStatement getPreparedStatement(String query, int key) throws SQLException {
        return getConnection().prepareStatement(query, key);
    }
}
