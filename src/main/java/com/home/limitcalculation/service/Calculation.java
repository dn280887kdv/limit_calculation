/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.limitcalculation.service;

import com.home.limitcalculation.dao.daoImpl.CreditDAOImpl;
import com.home.limitcalculation.entity.Client;
import com.home.limitcalculation.utility.LimitCalc;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Denis
 */
public class Calculation {

    private Client client;
    private LimitCalc limitCalc;
    private CreditDAOImpl creditDAOImpl;

    public Calculation() {
    }

    public Calculation(Client client) {
        this.client = client;
        this.limitCalc = new LimitCalc();
        this.creditDAOImpl = new CreditDAOImpl();
    }

    public void calculateAll() {
        int age = limitCalc.getAge(client.getDateBirthday());
        try {
            BigDecimal salary = limitCalc.monthSalaryConvert(client.getMonthSalary(), client.getCurrSalary());
            BigDecimal creditSum = creditDAOImpl.getCredits(client.getIdClient());
            String codeOperator = limitCalc.getCodeOperator(client.getPhone());
            double coefficient = limitCalc.getCoefficient(codeOperator);
            double limitItog = limitCalc.calc(client.getRequestLimit(), salary, coefficient, age, creditSum);

            System.out.println("salary = " + salary
                    + "  creditSum = " + creditSum
                    + "  codeOperator = " + codeOperator
                    + "  coefficient = " + coefficient
                    + "  limitItog = " + limitItog);

            if (limitItog > 0) {
                creditDAOImpl.setDecision(client.getIdClient(), "accept", limitItog);
                System.out.println("Лимит одобрен на сумму " + limitItog);
            } else {
                creditDAOImpl.setDecision(client.getIdClient(), "decline", limitItog);
                System.out.println("В кредитовании отказано, лимит = " + limitItog);
            }

        } catch (IOException ex) {
            Logger.getLogger(Calculation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
