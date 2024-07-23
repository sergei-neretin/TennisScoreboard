package com.sergeineretin.tennisScoreboard.listeners;

import com.sergeineretin.tennisScoreboard.service.OngoingMatchesService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
        context.setAttribute("ongoingMatchesService", ongoingMatchesService);
    }
}
