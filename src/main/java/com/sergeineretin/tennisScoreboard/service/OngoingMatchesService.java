package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.dao.PlayerDao;
import com.sergeineretin.tennisScoreboard.dao.impl.PlayerDaoImpl;
import com.sergeineretin.tennisScoreboard.dto.Match;
import com.sergeineretin.tennisScoreboard.model.MatchScoreModel;
import com.sergeineretin.tennisScoreboard.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class OngoingMatchesService {
    private static OngoingMatchesService instance;
    private HashMap<UUID, Match> ongoingMatches;
    private PlayerDao playerDao;

    private OngoingMatchesService() {
        ongoingMatches = new HashMap<>();
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Player.class)
                .addAnnotatedClass(MatchScoreModel.class)
                .buildSessionFactory();
        playerDao = new PlayerDaoImpl(factory);
    }

    public static synchronized OngoingMatchesService getInstance() {
        if (instance == null) {
            instance = new OngoingMatchesService();
        }
        return instance;
    }

    public String add(String name1, String name2) {
        UUID uuid = UUID.randomUUID();
        Match match = Match.builder().name1(name1).name2(name2).build();
        ongoingMatches.put(uuid, match);
        log.info(MessageFormat.format("Added new ongoing match: {0}",
                match));

        return uuid.toString();
    }

    private long getPlayerId(String name) {
        Optional<Player> optionalPlayer = playerDao.findByName(name);
        if (optionalPlayer.isPresent()) {
            return optionalPlayer.get().getId();
        } else {
            Player player = Player.builder().name(name).build();
            playerDao.save(player);
            return player.getId();
        }
    }

    public Match getMatch(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        return ongoingMatches.get(uuid);
    }


    public void remove(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        ongoingMatches.remove(uuid);
    }
}
