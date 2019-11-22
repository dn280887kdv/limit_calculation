/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.limitcalculation.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Denis
 */
public class Client {

    long idClient;
    LocalDate dateBirthday;
    String phone;
    String mail;
    String address;
    BigDecimal monthSalary;
    String currSalary;
    double requestLimit;

    public Client() {
    }

    public Client(long idClient, LocalDate dateBirthday, String phone, BigDecimal monthSalary, String currSalary) {
        this.idClient = idClient;
        this.dateBirthday = dateBirthday;
        this.phone = phone;
        this.monthSalary = monthSalary;
        this.currSalary = currSalary;
    }

    public Client(long idClient, LocalDate dateBirthday, String phone, String mail, String address, BigDecimal monthSalary, String currSalary, double requestLimit) {
        this.idClient = idClient;
        this.dateBirthday = dateBirthday;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.monthSalary = monthSalary;
        this.currSalary = currSalary;
        this.requestLimit = requestLimit;
    }

    public long getIdClient() {
        return idClient;
    }

    public LocalDate getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(LocalDate dateBirthday) {
        this.dateBirthday = dateBirthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary(BigDecimal monthSalary) {
        this.monthSalary = monthSalary;
    }

    public Enum getCurrSalary() {
        if (currSalary.equals("UAH")) {
            return CurrencyEnum.UAH;
        } else if (currSalary.equals("EUR")) {
            return CurrencyEnum.EUR;
        } else if (currSalary.equals("RUR")) {
            return CurrencyEnum.RUR;
        } else if (currSalary.equals("USD")) {
            return CurrencyEnum.USD;
        } else {
            return null;
        }
    }

    public void setCurrSalary(String currSalary) {
        this.currSalary = currSalary;
    }

    public double getRequestLimit() {
        return requestLimit;
    }

    public void setRequestLimit(double requestLimit) {
        this.requestLimit = requestLimit;
    }

}
