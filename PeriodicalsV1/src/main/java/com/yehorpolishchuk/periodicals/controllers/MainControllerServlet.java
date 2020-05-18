package com.yehorpolishchuk.periodicals.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.yehorpolishchuk.periodicals.datastructures.Address;
import com.yehorpolishchuk.periodicals.datastructures.Subscription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@WebServlet(name = "MainControllerServlet",
urlPatterns = {
        "/payment/*",
        "/reader/*",
        "/subscription/*",
        "/periodical/*",
        "/address/*"
})
public class MainControllerServlet extends HttpServlet {
    private final Logger logger = LogManager.getRootLogger();

    public static final String paymentControllerPath = "/payment";
    public static final String readerControllerPath = "/reader";
    public static final String subscriptionControllerPath = "/subscription";
    public static final String periodicalControllerPath = "/pariodical";
    public static final String addressControllerPath = "/address";

    private PaymentServlet paymentServlet = new PaymentServlet();
    private PeriodicalServlet periodicalServlet = new PeriodicalServlet();
    private ReaderServlet readerServlet = new ReaderServlet();
    private SubscriptionServlet subscriptionServlet = new SubscriptionServlet();
    private AddressServlet addressServlet = new AddressServlet();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if(servletPath.equals(readerControllerPath)) {
            readerServlet.doPost(request, response);
        } else if(servletPath.equals(paymentControllerPath)) {
            paymentServlet.doPost(request, response);
        } else if(servletPath.equals(addressControllerPath)) {
            addressServlet.doPost(request, response);
        } else if(servletPath.equals(subscriptionControllerPath)) {
            subscriptionServlet.doPost(request, response);
        } else if(servletPath.equals(periodicalControllerPath)) {
            periodicalServlet.doPost(request, response);
        } else {
            response.sendError(404, "Path not found");
            logger.error("Main controller error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if(servletPath.equals(readerControllerPath)) {
            readerServlet.doGet(request, response);
        } else if(servletPath.equals(paymentControllerPath)) {
            paymentServlet.doGet(request, response);
        } else if(servletPath.equals(addressControllerPath)) {
            addressServlet.doGet(request, response);
        } else if(servletPath.equals(subscriptionControllerPath)) {
            subscriptionServlet.doGet(request, response);
        } else if(servletPath.equals(periodicalControllerPath)) {
            periodicalServlet.doGet(request, response);
        } else {
            response.sendError(404, "Path not found");
            logger.error("Main controller error");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if(servletPath.equals(readerControllerPath)) {
            readerServlet.doPut(request, response);
        } else if(servletPath.equals(paymentControllerPath)) {
            paymentServlet.doPut(request, response);
        } else if(servletPath.equals(addressControllerPath)) {
            addressServlet.doPut(request, response);
        } else if(servletPath.equals(subscriptionControllerPath)) {
            subscriptionServlet.doPut(request, response);
        } else if(servletPath.equals(periodicalControllerPath)) {
            periodicalServlet.doPut(request, response);
        } else {
            response.sendError(404, "Path not found");
            logger.error("Main controller error");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if(servletPath.equals(readerControllerPath)) {
            readerServlet.doDelete(request, response);
        } else if(servletPath.equals(paymentControllerPath)) {
            paymentServlet.doDelete(request, response);
        } else if(servletPath.equals(addressControllerPath)) {
            addressServlet.doDelete(request, response);
        } else if(servletPath.equals(subscriptionControllerPath)) {
            subscriptionServlet.doDelete(request, response);
        } else if(servletPath.equals(periodicalControllerPath)) {
            periodicalServlet.doDelete(request, response);
        } else {
            response.sendError(404, "Path not found");
            logger.error("Main controller error");
        }
    }
}
