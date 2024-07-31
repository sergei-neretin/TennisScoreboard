package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.dto.Match;
import com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.*;

import java.util.Optional;

public class MatchScoreCalculationService {
    private final OngoingMatchesService ongoingMatchesService;
    private final SetsUpdater setsUpdater;
    private final WinnerGetter winnerGetter;

    public MatchScoreCalculationService(SetsUpdater setsUpdater, WinnerGetter winnerGetter) {
        this.setsUpdater = setsUpdater;
        this.winnerGetter = winnerGetter;
        ongoingMatchesService = OngoingMatchesService.getInstance();
    }

    public void updateScore(String uuid, String playerWhoWinsPoint) {
        Match match = ongoingMatchesService.getMatch(uuid);
        setsUpdater.updateSet(match, playerWhoWinsPoint);
    }

    public void updateScore(Match match, String playerWhoWinsPoint) {
        setsUpdater.updateSet(match, playerWhoWinsPoint);
    }

    public Optional<String> getWinner(String uuid) {
        Match match = ongoingMatchesService.getMatch(uuid);
        return winnerGetter.getWinner(match);
    }

    public Optional<String> getWinner(Match match) {
        return winnerGetter.getWinner(match);
    }
}
