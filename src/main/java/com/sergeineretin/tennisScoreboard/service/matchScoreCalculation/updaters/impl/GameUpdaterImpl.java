package com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.impl;

import com.sergeineretin.tennisScoreboard.Points;
import com.sergeineretin.tennisScoreboard.dto.Match;
import com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.GameUpdater;
import com.sergeineretin.tennisScoreboard.service.matchScoreCalculation.updaters.PointUpdater;

import java.util.Objects;

public class GameUpdaterImpl implements GameUpdater {
    private final PointUpdater pointUpdater;

    public GameUpdaterImpl(PointUpdater pointUpdater) {
        this.pointUpdater = pointUpdater;
    }
    @Override
    public void updateGame(Match match, String playerWhoWinsPoint) {
        if (Objects.equals(match.getName1(), playerWhoWinsPoint)
                && match.getPoints1() == Points.WIN_THIRD_PITCH && match.getPoints2() <= Points.WIN_SECOND_PITCH) {
            match.setGame1(match.getGame1() + 1);
            match.resetPoints();
        } else if (Objects.equals(match.getName2(), playerWhoWinsPoint)
                && match.getPoints2() == Points.WIN_THIRD_PITCH && match.getPoints1() <= Points.WIN_SECOND_PITCH) {
            match.setGame2(match.getGame2() + 1);
            match.resetPoints();
        } else if (Objects.equals(match.getName1(), playerWhoWinsPoint)
                && match.getPoints1() == Points.ADVANTAGE && match.getPoints2() == Points.WIN_THIRD_PITCH) {
            match.setGame1(match.getGame1() + 1);
            match.resetPoints();
        } else if(Objects.equals(match.getName2(), playerWhoWinsPoint)
                && match.getPoints2() == Points.ADVANTAGE && match.getPoints1() == Points.WIN_THIRD_PITCH)  {
            match.setGame2(match.getGame2() + 1);
            match.resetPoints();
        } else if(Objects.equals(match.getName1(), playerWhoWinsPoint)
                && match.getPoints1() == Points.WIN_THIRD_PITCH && match.getPoints2() == Points.ADVANTAGE )  {
            match.setPoints2(Points.WIN_THIRD_PITCH);
            pointUpdater.updatePoints(match, playerWhoWinsPoint);
        } else if(Objects.equals(match.getName2(), playerWhoWinsPoint)
                && match.getPoints2() == Points.WIN_THIRD_PITCH && match.getPoints1() == Points.ADVANTAGE )  {
            match.setPoints1(Points.WIN_THIRD_PITCH);
            pointUpdater.updatePoints(match, playerWhoWinsPoint);
        }  else {
            pointUpdater.updatePoints(match, playerWhoWinsPoint);
        }
    }
}
