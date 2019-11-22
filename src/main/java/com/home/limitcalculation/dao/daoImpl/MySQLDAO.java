/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.limitcalculation.dao.daoImpl;

import com.home.limitcalculation.dao.AbstractCreditDAO;

/**
 *
 * @author Denis
 */
public abstract class MySQLDAO extends AbstractCreditDAO {

    public MySQLDAO() {
        super("jdbc:mysql://localhost:3306/credit_calc",
                "root",
                "1234",
                "com.mysql.cj.jdbc.Driver");
    }

}
