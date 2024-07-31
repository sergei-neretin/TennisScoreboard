package com.sergeineretin.tennisScoreboard.controllers;

import com.sergeineretin.tennisScoreboard.dto.MatchDto;
import com.sergeineretin.tennisScoreboard.exceptions.PageException;
import com.sergeineretin.tennisScoreboard.exceptions.PageFormatException;
import com.sergeineretin.tennisScoreboard.service.FindingMatchesByPlayerNameService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet("/matches")
public class MatchesController extends HttpServlet {
    private FindingMatchesByPlayerNameService findingMatchesByPlayerNameService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        findingMatchesByPlayerNameService = FindingMatchesByPlayerNameService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long page = getPage(req);
            String filterByPlayerName = req.getParameter("filter_by_player_name");
            req.setAttribute("filter_by_player_name", filterByPlayerName);

            List<MatchDto> matches = findingMatchesByPlayerNameService.find(filterByPlayerName, page);
            req.setAttribute("matches", matches);

            Long numberOfPage = getNumberOfPage(page, filterByPlayerName);
            req.setAttribute("numberOfPages", numberOfPage);

            req.getRequestDispatcher("matches.jsp").forward(req, resp);
        } catch (PageFormatException e) {
            log.info(e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (PageException e) {
            log.info(e.getMessage());
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    private Long getNumberOfPage(Long page, String filterByPlayerName) {
        long numberOfPages = findingMatchesByPlayerNameService.getNumberOfPages(filterByPlayerName);
        if (page > numberOfPages) {
            throw new PageException("Page not found.");
        }
        return numberOfPages;
    }

    private Long getPage(HttpServletRequest req) {
        try {
            String pageString = req.getParameter("page");
            if (pageString == null) {
                return 1L;
            } else {
                return Long.parseLong(pageString);
            }
        } catch (NumberFormatException e) {
            throw new PageFormatException("Invalid page format");
        }
    }
}
