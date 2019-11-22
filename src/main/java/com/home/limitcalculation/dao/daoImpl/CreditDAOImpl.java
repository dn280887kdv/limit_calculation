/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.limitcalculation.dao.daoImpl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Denis
 */
public class CreditDAOImpl extends MySQLDAO {

    private static final String SETDECISION = "UPDATE client\n"
            + "SET decision = ?, limitItog = ?\n"
            + "WHERE idClient = ?";

    private static final String GETCREDITS = "SELECT sum(amtCredit) AS sum "
            + "FROM credit\n"
            + "WHERE idClient = ? AND stateCredit = 'O' ";

    @Override
    public BigDecimal getCredits(long idClient) {
        PreparedStatement preparedStatement = null;
        BigDecimal result = null;
        try {
            preparedStatement = getPreparedStatement(GETCREDITS);
            preparedStatement.setLong(1, idClient);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("суммы " + resultSet.toString());
                result = resultSet.getBigDecimal("sum").abs();
                System.out.println("Сумма получилась: " + result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public void setDecision(long idClient, String decision, double limitItog) {

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getPreparedStatement(SETDECISION);
            preparedStatement.setString(1, decision);
            preparedStatement.setDouble(2, limitItog);
            preparedStatement.setLong(3, idClient);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
