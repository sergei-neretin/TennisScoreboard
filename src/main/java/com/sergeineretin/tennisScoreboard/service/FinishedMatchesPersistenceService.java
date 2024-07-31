package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.dao.MatchDao;
import com.sergeineretin.tennisScoreboard.dao.PlayerDao;
import com.sergeineretin.tennisScoreboard.dao.impl.MatchDaoImpl;
import com.sergeineretin.tennisScoreboard.dao.impl.PlayerDaoImpl;
import com.sergeineretin.tennisScoreboard.dto.Match;
import com.sergeineretin.tennisScoreboard.dto.MatchDto;
import com.sergeineretin.tennisScoreboard.dto.MatchScoreDto;
import com.sergeineretin.tennisScoreboard.model.MatchScoreModel;
import com.sergeineretin.tennisScoreboard.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;

import java.text.MessageFormat;


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
        MatchDto matchDto = modelMapper.map(matchScoreModel, MatchDto.class);
        return MatchScoreDto.builder()
                .player1(matchDto.getPlayer1())
                .player2(matchDto.getPlayer2())
                .winner(matchDto.getWinner())
                .score1(match.getSet1())
                .score2(match.getSet2())
                .build();
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
