package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.dao.MatchDao;
import com.sergeineretin.tennisScoreboard.dao.PlayerDao;
import com.sergeineretin.tennisScoreboard.dao.impl.MatchDaoImpl;
import com.sergeineretin.tennisScoreboard.dao.impl.PlayerDaoImpl;
import com.sergeineretin.tennisScoreboard.model.Match;
import com.sergeineretin.tennisScoreboard.model.MatchScoreModel;
import com.sergeineretin.tennisScoreboard.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;

import java.text.MessageFormat;
import java.util.Objects;
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

    public void writeMatch(Match match, String winnerName) {
        MatchScoreModel matchScoreModel = convertToMatchScoreModel(match, winnerName);
        matchDao.save(matchScoreModel);
        log.info(MessageFormat.format("Written match: {0}", matchScoreModel));
    }

    private MatchScoreModel convertToMatchScoreModel(Match match, String winnerName) {
        Player player1 = findOrSavePlayer(match.getName1());
        Player player2 = findOrSavePlayer(match.getName2());

        if (Objects.equals(match.getName1(), winnerName)) {
            return MatchScoreModel.builder().player1(player1).player2(player2)
                    .winner(player1)
                    .build();
        } else {
            return MatchScoreModel.builder().player1(player1).player2(player2)
                    .winner(player2)
                    .build();
        }
    }

    private Player findOrSavePlayer(String name) {
        Optional<Player> optionalPlayer = playerDao.findByName(name);
        if (optionalPlayer.isPresent()) {
            return optionalPlayer.get();
        } else {
            Player player = Player.builder().name(name).build();
            playerDao.save(player);
            return player;
        }
    }
}
