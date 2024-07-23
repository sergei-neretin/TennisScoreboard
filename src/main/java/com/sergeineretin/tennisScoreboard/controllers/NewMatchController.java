package com.sergeineretin.tennisScoreboard.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/new_match")
public class NewMatchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new_match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstPlayerName = req.getParameter("player1");
        String secondPlayerName = req.getParameter("player2");
        if (firstPlayerName.equals(secondPlayerName)) {
            req.setAttribute("player1", firstPlayerName);
            req.setAttribute("player2", secondPlayerName);
            req.getRequestDispatcher("new_match.jsp").forward(req, resp);
        }
    }
}
