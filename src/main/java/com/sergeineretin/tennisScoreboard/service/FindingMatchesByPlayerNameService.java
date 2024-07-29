package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.dao.impl.MatchDaoImpl;
import com.sergeineretin.tennisScoreboard.model.MatchScoreModel;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class FindingMatchesByPlayerNameService {
    private final MatchDaoImpl matchScoreDao;

    public FindingMatchesByPlayerNameService() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(MatchScoreModel.class)
                .buildSessionFactory();
        this.matchScoreDao = new MatchDaoImpl(factory);
    }

    public List<MatchScoreModel> find(String name) {
        return new ArrayList<>();
    }
}
