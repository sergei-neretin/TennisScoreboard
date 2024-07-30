package com.sergeineretin.tennisScoreboard.dao;

import com.sergeineretin.tennisScoreboard.model.MatchScoreModel;

import java.util.List;
import java.util.Optional;

public interface MatchDao {
    Optional<MatchScoreModel> findById(long id);
    List<MatchScoreModel> findByPlayerName(String name, int page);
    List<MatchScoreModel> findAll();
    void save(MatchScoreModel matchScoreModel);
    void delete(MatchScoreModel matchScoreModel);
}
