/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.limitcalculation.entity;

import com.home.limitcalculation.service.Calculation;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;

/**
 *
 * @author Denis
 */
public class Main {

    public static void main(String[] args) throws ParseException {
        Client client = new Client(123213, LocalDate.of(2010, 2, 14), "+380634243785", "ASD@GMAIL.RU", "TEST 12/12", new BigDecimal(5000.25), "USD", 5000);
        Calculation calculation = new Calculation(client);
        calculation.calculateAll();
    }

}
