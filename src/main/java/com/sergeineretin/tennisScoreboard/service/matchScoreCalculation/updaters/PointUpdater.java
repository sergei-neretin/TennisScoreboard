package com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters;

import com.sergeineretin.tennisScoreboard.model.Match;

public interface PointUpdater {
    void updatePoints(Match match, String playerWhoWinsPoint);
}
