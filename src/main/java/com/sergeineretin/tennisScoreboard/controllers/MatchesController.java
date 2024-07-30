package com.sergeineretin.tennisScoreboard.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/matches")
public class MatchesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int page = getPage(req);
            String filterByPlayerName = req.getParameter("filter_by_player_name");
            req.setAttribute("filter_by_player_name", filterByPlayerName);
            req.getRequestDispatcher("matches.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private int getPage(HttpServletRequest req) {
        String pageString = req.getParameter("page");
        if (pageString == null) {
            return 1;
        } else {
            return Integer.parseInt(pageString);
        }
    }
}
