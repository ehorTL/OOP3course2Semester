package com.yehorpolishchuk.periodicals.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.yehorpolishchuk.periodicals.controllers.util.ResponseUtil;
import com.yehorpolishchuk.periodicals.datastructures.Address;
import com.yehorpolishchuk.periodicals.datastructures.Subscription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.yehorpolishchuk.periodicals.controllers.util.ResponseUtil.setCORSEnabledForAll;

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
    public static final String periodicalControllerPath = "/periodical";
    public static final String addressControllerPath = "/address";

    private PaymentServlet paymentServlet = new PaymentServlet();
    private PeriodicalServlet periodicalServlet = new PeriodicalServlet();
    private SubscriptionServlet subscriptionServlet = new SubscriptionServlet();
    private AddressServlet addressServlet = new AddressServlet();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCORSEnabledForAll(response);
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept" );

        String servletPath = request.getServletPath();

        if(servletPath.equals(paymentControllerPath)) {
            paymentServlet.doPost(request, response);
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
        setCORSEnabledForAll(response);
        String servletPath = request.getServletPath();

        if(servletPath.equals(paymentControllerPath)) {
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
        setCORSEnabledForAll(response);
        String servletPath = request.getServletPath();

        if(servletPath.equals(periodicalControllerPath)) {
            periodicalServlet.doPut(request, response);
        } else {
            response.sendError(404, "Path not found");
            logger.error("Main controller error");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setCORSEnabledForAll(response);
        String servletPath = request.getServletPath();

        if(servletPath.equals(periodicalControllerPath)) {
            periodicalServlet.doDelete(request, response);
        } else {
            response.sendError(404, "Path not found");
            logger.error("Main controller error");
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCORSEnabledForAll(resp);
        resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept" );
        super.doOptions(req, resp);
    }
}
