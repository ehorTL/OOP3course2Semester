package com.yehorpolishchuk.periodicals.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yehorpolishchuk.periodicals.JsonHandler;
import com.yehorpolishchuk.periodicals.controllers.util.ResponseUtil;
import com.yehorpolishchuk.periodicals.datastructures.SubscribeUtil;
import com.yehorpolishchuk.periodicals.datastructures.Subscription;
import com.yehorpolishchuk.periodicals.exceptions.ServerException;
import com.yehorpolishchuk.periodicals.services.SubscriptionService;
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

@WebServlet(name = "SubscriptionServlet")
public class SubscriptionServlet extends HttpServlet {

    private final Logger logger = LogManager.getRootLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] urls = request.getPathInfo().split("/");

        if (urls.length == 2 && urls[1].equals("add")){
            Subscription subscription = new ObjectMapper().readValue(jsonBodyFromRequest(request, response), Subscription.class);
            try {
                subscription = SubscriptionService.addSubscriptionWithoutPayment(subscription);
                if (subscription == null) {
                    response.sendError(400, "Bad request");
                } else {
                    sendJSON(subscription, response);
                }
            } catch (ServerException e) {
                response.sendError(500);
            }
        } else if (urls.length == 3 && urls[1].equals("add") && urls[2].equals("full")){
            SubscribeUtil subscribeUtil = new ObjectMapper().readValue(jsonBodyFromRequest(request, response), SubscribeUtil.class);
            try {
                ResponseUtil.setCORSEnabledForAll(response);
                subscribeUtil = SubscriptionService.addSubscription(subscribeUtil);
                if (subscribeUtil == null) {
                    response.sendError(400, "Bad request");
                } else {
                    sendJSON(subscribeUtil, response);
                }
            } catch (ServerException e) {
                response.sendError(500);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] urls = request.getPathInfo().split("/");

        if (urls.length == 4 && urls[1].equals("price")){
            int periodicalId = Integer.parseInt(urls[2]);
            int termInMonth = Integer.parseInt(urls[3]);

            try {
                double priceCalculated = SubscriptionService.calculatePriceOfSubscription(periodicalId, termInMonth);
                if (Double.compare(priceCalculated, 0.0) == 0) {
                    response.sendError(500);
                } else {
                    JsonHandler.sendJSON(priceCalculated, response);
                }
            } catch (ServerException e) {
                response.sendError(500);
            }
        }
    }
}
