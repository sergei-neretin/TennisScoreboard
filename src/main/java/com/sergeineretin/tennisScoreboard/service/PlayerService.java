package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.dao.PlayerDao;
import com.sergeineretin.tennisScoreboard.dao.impl.PlayerDaoImpl;
import com.sergeineretin.tennisScoreboard.dto.Player;
import com.sergeineretin.tennisScoreboard.model.PlayerModel;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class PlayerService {
    private PlayerDao playerDao;
    private static PlayerService playerService;

    private  PlayerService () {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(PlayerModel.class)
                .buildSessionFactory();
        playerDao = new PlayerDaoImpl(factory);
    }

    public static synchronized PlayerService getInstance() {
        if (playerService == null) {
            playerService = new PlayerService();
        }
        return playerService;
    }

    public Player getPlayer(String name) {
        Optional<PlayerModel> optional = playerDao.findByName(name);

        if (optional.isPresent()) {
            return convertToDto(optional.get());
        } else {
            PlayerModel playerModel = PlayerModel.builder().name(name).build();
            playerDao.save(playerModel);
            return convertToDto(playerModel);
        }
    }

    public Player getPlayer(long id) {
        Optional<PlayerModel> optional = playerDao.findById(id);
        return convertToDto(optional.orElse(new PlayerModel()));
    }

    private Player convertToDto(PlayerModel model) {
        return new Player(model.getId(), model.getName());
    }
}
