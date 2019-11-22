/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.home.limitcalculation.main;

import com.google.gson.Gson;
import com.home.limitcalculation.entity.Client;
import com.home.limitcalculation.service.Calculation;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Denis
 */
@WebServlet("/calc")
public class MainServiceClass extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuffer obj = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                obj.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Client client = gson.fromJson(obj.toString(), Client.class);

        if (client.getIdClient() != 0 && client.getMonthSalary() != null && client.getCurrSalary() != null && client.getPhone() != null && client.getDateBirthday() != null) {
            Calculation calculation = new Calculation(client);
        } else {
            resp.sendError(400);
        }

    }

}
