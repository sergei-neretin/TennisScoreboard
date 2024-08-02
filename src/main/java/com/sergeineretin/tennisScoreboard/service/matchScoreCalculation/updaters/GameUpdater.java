package com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters;

import com.sergeineretin.tennisScoreboard.model.Match;

public interface GameUpdater {
    void updateGame(Match match, String playerWhoWinsPoint);
}
