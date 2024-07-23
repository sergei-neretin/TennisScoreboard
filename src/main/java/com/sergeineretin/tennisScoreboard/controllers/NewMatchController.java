package com.sergeineretin.tennisScoreboard.controllers;

import com.sergeineretin.tennisScoreboard.service.OngoingMatchesService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/new_match")
public class NewMatchController extends HttpServlet {
    private OngoingMatchesService ongoingMatchesService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ongoingMatchesService = (OngoingMatchesService) config
                .getServletContext()
                .getAttribute("ongoingMatchesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new_match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name1 = req.getParameter("player1");
        String name2 = req.getParameter("player2");
        if (name1.equals(name2)) {
            req.setAttribute("player1", name1);
            req.setAttribute("player2", name2);
            req.getRequestDispatcher("new_match.jsp").forward(req, resp);
        }
        ongoingMatchesService.add(name1, name2);
    }
}
