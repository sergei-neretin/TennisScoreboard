package com.sergeineretin.tennisScoreboard.listeners;

import com.sergeineretin.tennisScoreboard.service.OngoingMatchesService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
        sce.getServletContext().setAttribute("ongoingMatchesService", ongoingMatchesService);
    }
}
