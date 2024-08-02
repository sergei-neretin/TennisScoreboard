package com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.impl;

import com.sergeineretin.tennisScoreboard.Points;
import com.sergeineretin.tennisScoreboard.model.Match;
import com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.PointUpdater;

import java.util.Objects;

public class PointUpdaterImpl implements PointUpdater {
    @Override
    public void updatePoints(Match match, String playerWhoWinsPoint) {
        if (Objects.equals(playerWhoWinsPoint, match.getName1())) {
            int newPoints = calculateNewPoints(match.getPoints1());
            match.setPoints1(newPoints);
        } else {
            int newPoints = calculateNewPoints(match.getPoints2());
            match.setPoints2(newPoints);
        }
    }

    private int calculateNewPoints(int points) {
        if (points == 0) {
            return Points.WIN_FIRST_PITCH;
        } else if (points == Points.WIN_FIRST_PITCH) {
            return Points.WIN_SECOND_PITCH;
        } else if(points == Points.WIN_SECOND_PITCH) {
            return Points.WIN_THIRD_PITCH;
        } else {
            return Points.ADVANTAGE;
        }
    }
}
