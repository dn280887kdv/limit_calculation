/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.limitcalculation.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Denis
 */
public class CreditInfo {

    long idClient;
    long idCredit;
    BigDecimal amtCredit;
    Date dateStart;
    char stateCredit;

    public CreditInfo() {
    }

    public CreditInfo(long idClient, long idCredit, BigDecimal amtCredit, Date dateStart, char stateCredit) {
        this.idClient = idClient;
        this.idCredit = idCredit;
        this.amtCredit = amtCredit;
        this.dateStart = dateStart;
        this.stateCredit = stateCredit;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public long getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(long idCredit) {
        this.idCredit = idCredit;
    }

    public BigDecimal getAmtCredit() {
        return amtCredit;
    }

    public void setAmtCredit(BigDecimal amtCredit) {
        this.amtCredit = amtCredit;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public char getStateCredit() {
        return stateCredit;
    }

    public void setStateCredit(char stateCredit) {
        this.stateCredit = stateCredit;
    }

}
