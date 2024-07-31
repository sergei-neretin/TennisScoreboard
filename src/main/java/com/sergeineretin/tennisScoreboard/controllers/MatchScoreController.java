package com.sergeineretin.tennisScoreboard.controllers;

import com.sergeineretin.tennisScoreboard.dto.Match;
import com.sergeineretin.tennisScoreboard.dto.MatchScoreDto;
import com.sergeineretin.tennisScoreboard.service.FinishedMatchesPersistenceService;
import com.sergeineretin.tennisScoreboard.service.MatchScoreCalculationService;
import com.sergeineretin.tennisScoreboard.service.OngoingMatchesService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {
    private MatchScoreCalculationService matchScoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private OngoingMatchesService ongoingMatchesService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        matchScoreCalculationService = new MatchScoreCalculationService();
        finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
        ongoingMatchesService = OngoingMatchesService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        long playerWhoWinsPoint = Long.parseLong(req.getParameter("playerId"));
        matchScoreCalculationService.updateScore(uuid, playerWhoWinsPoint);
        Optional<Long> winnerId = matchScoreCalculationService.getWinner(uuid);
        if (winnerId.isPresent()) {
            Match match = ongoingMatchesService.getMatch(uuid);
            ongoingMatchesService.remove(uuid);
            MatchScoreDto matchScoreDto = finishedMatchesPersistenceService.writeMatch(match, winnerId.get());
            req.setAttribute("match", matchScoreDto);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/finished-match.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/match-score?uuid=" + uuid);
        }
    }
}
