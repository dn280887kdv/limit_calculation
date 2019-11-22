/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.limitcalculation.entity;

/**
 *
 * @author Denis
 */
public enum CodeOperatorEnum {

    KYIVSTAR(0.95),
    VODAFONE(0.94),
    LIFECELL(0.92),
    OTHER(0.90);

    private double code;

    private CodeOperatorEnum(double code) {
        this.code = code;
    }

    public double getCode() {
        return code;
    }
}
