package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.dao.PlayerDao;
import com.sergeineretin.tennisScoreboard.dao.impl.PlayerDaoImpl;
import com.sergeineretin.tennisScoreboard.model.Match;
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
    private HashMap<UUID, Match> ongoingMatches;
    PlayerDao playerDao;

    public OngoingMatchesService() {
        ongoingMatches = new HashMap<>();
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Player.class)
                .buildSessionFactory();
        playerDao = new PlayerDaoImpl(factory);
    }

    public void add(String name1, String name2) {
        UUID uuid = UUID.randomUUID();
        Player player1 = getPlayer(name1);
        Player player2 = getPlayer(name2);
        Match match = Match.builder().player1(player1).player2(player2).build();
        ongoingMatches.put(uuid, match);
        log.info(MessageFormat.format("Added new ongoing match: UUID = {0}, player1 = {1}, player2 = {2}.",
                uuid,
                match.getPlayer1().getId(),
                match.getPlayer2().getId()));
    }

    public Match getMatch(UUID uuid) {
        return ongoingMatches.get(uuid);
    }

    private Player getPlayer(String name) {
        Optional<Player> optional = playerDao.findByName(name);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            Player player = Player.builder().name(name).build();
            playerDao.save(player);
            return player;
        }
    }
}
