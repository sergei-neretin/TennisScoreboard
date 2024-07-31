package com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters;

import com.sergeineretin.tennisScoreboard.dto.Match;

public interface SetsUpdater {
    void updateSet(Match match, String playerWhoWinsPoint);
}
