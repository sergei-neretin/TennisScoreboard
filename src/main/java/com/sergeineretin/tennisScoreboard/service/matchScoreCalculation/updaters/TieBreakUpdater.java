package com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters;

import com.sergeineretin.tennisScoreboard.model.Match;

public interface TieBreakUpdater {
    void updateTieBreak(Match match, String playerWhoWinsPoint);
    boolean tieBreakCondition(Match match);
}
