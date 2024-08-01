package com.sergeineretin.tennisScoreboard.controllers;

import com.sergeineretin.tennisScoreboard.service.OngoingMatchesService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {
    private OngoingMatchesService ongoingMatchesService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ongoingMatchesService = OngoingMatchesService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name1 = req.getParameter("player1");
        String name2 = req.getParameter("player2");
        if (name1.equals(name2)) {
            req.setAttribute("player1", name1);
            req.setAttribute("player2", name2);
            req.getRequestDispatcher("new-match.jsp").forward(req, resp);
        }
        String contextPath = req.getContextPath();

        String uuidString = ongoingMatchesService.add(name1, name2);
        resp.sendRedirect(contextPath + "/match-score?uuid=" + uuidString);
    }
}
