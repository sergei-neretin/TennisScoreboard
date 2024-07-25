package com.sergeineretin.tennisScoreboard.dao;

import com.sergeineretin.tennisScoreboard.model.PlayerModel;

import java.util.List;
import java.util.Optional;

public interface PlayerDao {
    Optional<PlayerModel> findById(long id);
    Optional<PlayerModel> findByName(String name);
    List<PlayerModel> findAll();
    void save(PlayerModel match);
    void delete(PlayerModel match);
}
