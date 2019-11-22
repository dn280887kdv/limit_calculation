/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.limitcalculation.dao;

import java.math.BigDecimal;

/**
 *
 * @author Denis
 */
public interface CreditDAO {

    BigDecimal getCredits(long idClient);

    void setDecision(long idClient, String decision, double limitItog);

    void openConnection(String dbName);

    void closeConnect();

}
