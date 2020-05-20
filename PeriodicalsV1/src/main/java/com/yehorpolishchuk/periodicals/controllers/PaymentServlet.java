package com.yehorpolishchuk.periodicals.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yehorpolishchuk.periodicals.datastructures.Payment;
import com.yehorpolishchuk.periodicals.datastructures.Periodical;
import com.yehorpolishchuk.periodicals.exceptions.ServerException;
import com.yehorpolishchuk.periodicals.services.PaymentService;
import com.yehorpolishchuk.periodicals.services.PeriodicalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.yehorpolishchuk.periodicals.JsonHandler.jsonBodyFromRequest;
import static com.yehorpolishchuk.periodicals.JsonHandler.sendJSON;

@WebServlet(name = "PaymentServlet")
public class PaymentServlet extends HttpServlet {

    private final Logger logger = LogManager.getRootLogger();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] urls = request.getPathInfo().split("/");

        if (urls.length == 2 && urls[1].equals("add")){
            Payment payment = new ObjectMapper().readValue(jsonBodyFromRequest(request, response), Payment.class);
            try {
                payment = PaymentService.addPayment(payment);
                if (payment == null) {
                    response.sendError(400, "Bad request");
                } else {
                    sendJSON(payment, response);
                }
            } catch (ServerException e) {
                response.sendError(500);
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
