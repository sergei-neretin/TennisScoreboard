package com.sergeineretin.tennisScoreboard.controllers;

import com.sergeineretin.tennisScoreboard.dto.Match;
import com.sergeineretin.tennisScoreboard.exceptions.MatchNotFoundException;
import com.sergeineretin.tennisScoreboard.service.FinishedMatchesPersistenceService;
import com.sergeineretin.tennisScoreboard.service.MatchScoreCalculationService;
import com.sergeineretin.tennisScoreboard.service.OngoingMatchesService;
import com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.MatchScoreCalculationFacade;
import com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.SetsUpdater;
import com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.WinnerGetter;
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
        SetsUpdater setsUpdater = MatchScoreCalculationFacade.getSetUpdater();
        WinnerGetter winnerGetter = MatchScoreCalculationFacade.getWinnerGetter();
        matchScoreCalculationService = new MatchScoreCalculationService(setsUpdater, winnerGetter);
        finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
        ongoingMatchesService = OngoingMatchesService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String uuidString = req.getParameter("uuid");
            Match match = ongoingMatchesService.getMatch(uuidString);
            req.setAttribute("match", match);
            req.getRequestDispatcher("match-score.jsp").forward(req, resp);
        } catch (MatchNotFoundException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String uuid = req.getParameter("uuid");
            String nameOfPointWinner = req.getParameter("playerName");
            matchScoreCalculationService.updateScore(uuid, nameOfPointWinner);
            Optional<String> winnerName = matchScoreCalculationService.getWinner(uuid);
            String contextPath = req.getContextPath();

            if (winnerName.isPresent()) {
                Match match = ongoingMatchesService.getMatch(uuid);
                ongoingMatchesService.remove(uuid);
                finishedMatchesPersistenceService.writeMatch(match, winnerName.get());
                req.setAttribute("match", match);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/finished-match.jsp");
                dispatcher.forward(req, resp);
            } else {
                resp.sendRedirect(contextPath + "/match-score?uuid=" + uuid);
            }
        } catch (MatchNotFoundException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }
}
