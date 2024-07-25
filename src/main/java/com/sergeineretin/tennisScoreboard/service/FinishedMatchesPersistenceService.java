package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.dao.impl.MatchScoreDaoImpl;
import com.sergeineretin.tennisScoreboard.dto.Match;
import com.sergeineretin.tennisScoreboard.model.MatchScoreModel;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.MessageFormat;
@Slf4j
public class FinishedMatchesPersistenceService {
    private MatchScoreDaoImpl matchScoreDao;

    public FinishedMatchesPersistenceService() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(MatchScoreModel.class)
                .buildSessionFactory();
        this.matchScoreDao = new MatchScoreDaoImpl(factory);
    }

    public void writeMatch(Match match, Long winnerId) {
        MatchScoreModel matchScoreModel = convertToModel(match);
        matchScoreModel.setWinnerId(winnerId);
        matchScoreDao.save(matchScoreModel);
        log.info(MessageFormat.format("Written match: ID = {0}, player1 = {1}, player2 = {2}, winner = {3}.",
                matchScoreModel.getId(),
                matchScoreModel.getPlayer1Id(),
                matchScoreModel.getPlayer2Id(),
                matchScoreModel.getWinnerId()));
    }

    private MatchScoreModel convertToModel(Match match) {
        return MatchScoreModel.builder()
                .player1Id(match.getId1())
                .player2Id(match.getId2())
                .build();
    }
}
