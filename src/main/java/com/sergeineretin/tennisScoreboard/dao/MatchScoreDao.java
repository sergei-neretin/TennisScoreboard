package com.sergeineretin.tennisScoreboard.dao;

import com.sergeineretin.tennisScoreboard.model.MatchScoreModel;

import java.util.List;
import java.util.Optional;

public interface MatchScoreDao {
    Optional<MatchScoreModel> findById(long id);
    List<MatchScoreModel> findAll();
    void save(MatchScoreModel match);
    void delete(MatchScoreModel match);
}
