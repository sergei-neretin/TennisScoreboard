package com.sergeineretin.tennisScoreboard.dao;

import com.sergeineretin.tennisScoreboard.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerDao {
    Optional<Player> findById(int id);
    Optional<Player> findByName(String name);
    List<Player> findAll();
    void save(Player match);
    void delete(Player match);
}
