package com.yehorpolishchuk.periodicals.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yehorpolishchuk.periodicals.JsonHandler;
import com.yehorpolishchuk.periodicals.datastructures.Periodical;
import com.yehorpolishchuk.periodicals.exceptions.ServerException;
import com.yehorpolishchuk.periodicals.services.PeriodicalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import static com.yehorpolishchuk.periodicals.JsonHandler.*;

@WebServlet(name = "PeriodicalServlet")
public class PeriodicalServlet extends HttpServlet {
    private final Logger logger = LogManager.getRootLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] urls = request.getPathInfo().split("/");

        if (urls.length == 3 && urls[1].equals("admin") && urls[2].equals("add")) {
            Periodical periodical = new ObjectMapper().readValue(jsonBodyFromRequest(request, response), Periodical.class);
            try {
                periodical = PeriodicalService.addPeriodical(periodical);
                if (periodical == null) {
                    response.sendError(400, "Bad request");
                } else {
                    sendJSON(periodical, response);
                }
            } catch (ServerException e) {
                response.sendError(500);
            }
        } else {
            response.sendError(404, "Path not found");
            logger.error("Path not found");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] urls = request.getPathInfo().split("/");

        if (urls.length == 2){
            if (urls[1].equals("all")){
                List<Periodical> periodicals = null;
                try {
                    periodicals = PeriodicalService.getAllPeriodicals();
                    if (periodicals == null || periodicals.size() == 0){
                        response.sendError(404, "Resource not found");
                    } else {
                        JsonHandler.sendJSON(periodicals, response);
                    }

                } catch (ServerException e) {
                    response.sendError(500);
                }
            } else {
                int id = Integer.parseInt(urls[1]);
                try {
                    Periodical periodical = PeriodicalService.getPeriodical(id);
                    if (periodical == null) {
                        response.sendError(404, "Resorce not found");
                    } else {
                        JsonHandler.sendJSON(periodical, response);
                    }
                } catch (ServerException e) {
                    response.sendError(500);
                }
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] urls = request.getPathInfo().split("/");

        if (urls.length == 4 && urls[1].equals("admin") && urls[2].equals("delete")) {
            int id = Integer.parseInt(urls[3]);
            try {
                PeriodicalService.deletePeriodical(id);
            } catch (ServerException e) {
                response.sendError(500);
            }
        } else {
            response.sendError(404, "Path not found");
            logger.error("Path " + request.getPathInfo() + " not found");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] urls = request.getPathInfo().split("/");

        if (urls.length == 3 && urls[1].equals("admin") && urls[2].equals("update")) {
            Periodical periodical = new ObjectMapper().readValue(jsonBodyFromRequest(request, response), Periodical.class);
            try {
                periodical = PeriodicalService.editPeriodical(periodical);
                if (periodical == null) {
                    response.sendError(400, "Bad request");
                } else {
                    sendJSON(periodical, response);
                }
            } catch (ServerException e) {
                response.sendError(500);
            }
        } else {
            response.sendError(404, "Path not found");
            logger.error("Path not found");
        }
    }
}
