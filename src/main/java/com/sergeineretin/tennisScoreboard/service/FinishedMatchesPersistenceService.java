package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.dao.MatchDao;
import com.sergeineretin.tennisScoreboard.dao.PlayerDao;
import com.sergeineretin.tennisScoreboard.dao.impl.MatchDaoImpl;
import com.sergeineretin.tennisScoreboard.dao.impl.PlayerDaoImpl;
import com.sergeineretin.tennisScoreboard.dto.Match;
import com.sergeineretin.tennisScoreboard.dto.MatchScoreDto;
import com.sergeineretin.tennisScoreboard.model.MatchScoreModel;
import com.sergeineretin.tennisScoreboard.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;

import java.text.MessageFormat;
import java.util.Optional;

@Slf4j
public class FinishedMatchesPersistenceService {
    private MatchDao matchDao;
    private PlayerDao playerDao;
    ModelMapper modelMapper;

    public FinishedMatchesPersistenceService() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(MatchScoreModel.class)
                .addAnnotatedClass(MatchScoreModel.class)
                .buildSessionFactory();
        this.matchDao = new MatchDaoImpl(factory);
        this.playerDao = new PlayerDaoImpl(factory);
        this.modelMapper = new ModelMapper();
    }

    public MatchScoreDto writeMatch(Match match, Long winnerId) {
        MatchScoreModel matchScoreModel = convertToMatchScoreModel(match, winnerId);
        matchDao.save(matchScoreModel);
        log.info(MessageFormat.format("Written match: {0}", matchScoreModel));
        MatchScoreDto matchScoreDto = modelMapper.map(matchScoreModel, MatchScoreDto.class);
        matchScoreDto.setScore1(match.getSet1());
        matchScoreDto.setScore2(match.getSet2());
        return matchScoreDto;
    }

    private MatchScoreModel convertToMatchScoreModel(Match match, Long winnerId) {
        Player player1 = playerDao.findById(match.getId1()).get();
        Player player2 = playerDao.findById(match.getId2()).get();

        if (match.getId1() == winnerId) {
            return MatchScoreModel.builder().player1(player1).player2(player2)
                    .winner(player1)
                    .build();
        } else {
            return MatchScoreModel.builder().player1(player1).player2(player2)
                    .winner(player2)
                    .build();
        }
    }
}
