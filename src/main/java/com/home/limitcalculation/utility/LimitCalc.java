/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.limitcalculation.utility;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.home.limitcalculation.entity.CodeOperatorEnum;
import com.home.limitcalculation.entity.CourseFromAPI;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Denis
 */
public class LimitCalc {

    public LimitCalc() {
    }

    public BigDecimal monthSalaryConvert(BigDecimal sum, Enum curr) throws IOException {
        if (curr != null) {
            if (!(curr.name().equals("UAH"))) {
                HttpGet request = new HttpGet("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5");
                String result = null;
                double needCourse = 0;
                BigDecimal salary = null;

                try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse response = httpClient.execute(request)) {
                    HttpEntity entity = response.getEntity();

                    if (entity != null) {
                        result = EntityUtils.toString(entity);
                        Gson gson = new Gson();
                        List<CourseFromAPI> courseList = gson.fromJson(result, new TypeToken<List<CourseFromAPI>>() {
                        }.getType());

                        for (CourseFromAPI temp : courseList) {
                            String s = null;
                            if (temp.getCcy().equals(curr.name())) {
                                needCourse = temp.getBuy();
                                salary = sum.multiply(new BigDecimal(needCourse));
                            }
                        }
                    }

                }
                return salary;
            } else {
                return sum;
            }
        } else {
            return null;
        }
    }

    public int getAge(LocalDate birth) {

        LocalDate endDate = LocalDate.now();
        Period period = Period.between(birth, endDate);
        System.out.println(period.getYears());
        return period.getYears();
    }

    public String getCodeOperator(String phone) {
        char[] chars = phone.toCharArray();
        StringBuilder result = new StringBuilder();
        result.append(chars[3]).append(chars[4]).append(chars[5]);
        return result.toString();
    }

    public double getCoefficient(String code) {
        CodeOperatorEnum operator = null;
        if (code.equals("097") || code.equals("067")) {
            operator = CodeOperatorEnum.KYIVSTAR;
            return operator.getCode();
        } else if (code.equals("066") || code.equals("096")) {
            operator = CodeOperatorEnum.VODAFONE;
            return operator.getCode();
        } else if (code.equals("063") || code.equals("093")) {
            operator = CodeOperatorEnum.LIFECELL;
            return operator.getCode();
        } else {
            operator = CodeOperatorEnum.OTHER;
            return operator.getCode();
        }
    }

    public double calc(double requestLimit, BigDecimal salary, double coefficient, int age, BigDecimal creditSum) {

        double limitItog = 0;
        if ((creditSum.multiply(new BigDecimal(100)).divide(salary, new MathContext(2, RoundingMode.HALF_UP))).compareTo(new BigDecimal(60)) == 1 || age < 18) {
            limitItog = 0;
        } else {
            limitItog = (new BigDecimal(coefficient).multiply(salary.subtract(creditSum))).doubleValue();
        }

        if (limitItog > requestLimit) {
            limitItog = requestLimit;
            return limitItog;
        } else {
            return limitItog;
        }
    }
}
