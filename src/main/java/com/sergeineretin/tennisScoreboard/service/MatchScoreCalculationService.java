package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.Points;
import com.sergeineretin.tennisScoreboard.dto.Match;

import java.util.Objects;
import java.util.Optional;

public class MatchScoreCalculationService {
    private final OngoingMatchesService ongoingMatchesService;

    public MatchScoreCalculationService() {
        ongoingMatchesService = OngoingMatchesService.getInstance();
    }
    public void updateScore(String uuid, String playerWhoWinsPoint) {
        Match match = ongoingMatchesService.getMatch(uuid);
        updateSet(match, playerWhoWinsPoint);
    }

    public void updateScore(Match match, String playerWhoWinsPoint) {
        updateSet(match, playerWhoWinsPoint);
    }

    private void updateSet(Match match, String playerWhoWinsPoint) {
        if (tieBreakCondition(match)) {
            updateTieBreak(match, playerWhoWinsPoint);
        } else {
            updateGame(match, playerWhoWinsPoint);
            if (match.getGame1() == 6 && match.getGame2() <= 4) {
                match.setSet1(match.getSet1() + 1);
                resetGames(match);
            } else if (match.getGame2() == 6 && match.getGame1() <= 4) {
                match.setSet2(match.getSet2() + 1);
                resetGames(match);
            } else if (match.getGame1() == 7 && match.getGame2() <= 5) {
                match.setSet1(match.getSet1() + 1);
                resetGames(match);
            } else if (match.getGame2() == 7 && match.getGame1() <= 5) {
                match.setSet2(match.getSet1() + 1);
                resetGames(match);
            }
        }
    }

    private void updateTieBreak(Match match, String playerWhoWinsPoint) {
        if (Objects.equals(playerWhoWinsPoint, match.getName1())) {
            match.setPoints1(match.getPoints1() + 1);
        } else {
            match.setPoints2(match.getPoints2() + 1);
        }

        if (match.getPoints1() == 7 && match.getPoints2() < 6) {
            match.setSet1(match.getSet1() + 1);
            resetGames(match);
        } else if (match.getPoints2() == 7 && match.getPoints1() < 6) {
            match.setSet2(match.getSet2() + 1);
            resetGames(match);
        } else if((match.getPoints1() == 6 && match.getPoints2() == 6)) {
            resetPoints(match);
        }
    }

    private void resetGames(Match match) {
        match.setGame1(0);
        match.setGame2(0);
        resetPoints(match);
    }

    private boolean tieBreakCondition(Match match) {
        return match.getGame1() == 6 && match.getGame2() == 6;
    }

    private void updateGame(Match match, String playerWhoWinsPoint) {
        if (Objects.equals(match.getName1(), playerWhoWinsPoint)
                && match.getPoints1() == Points.WIN_THIRD_PITCH && match.getPoints2() <= Points.WIN_SECOND_PITCH) {
            match.setGame1(match.getGame1() + 1);
            resetPoints(match);
        } else if (Objects.equals(match.getName2(), playerWhoWinsPoint)
                && match.getPoints2() == Points.WIN_THIRD_PITCH && match.getPoints1() <= Points.WIN_SECOND_PITCH) {
            match.setGame2(match.getGame2() + 1);
            resetPoints(match);
        } else if (Objects.equals(match.getName1(), playerWhoWinsPoint)
                && match.getPoints1() == Points.ADVANTAGE && match.getPoints2() == Points.WIN_THIRD_PITCH) {
            match.setGame1(match.getGame1() + 1);
            resetPoints(match);
        } else if(Objects.equals(match.getName2(), playerWhoWinsPoint)
                && match.getPoints2() == Points.ADVANTAGE && match.getPoints1() == Points.WIN_THIRD_PITCH)  {
            match.setGame2(match.getGame2() + 1);
            resetPoints(match);
        } else if(Objects.equals(match.getName1(), playerWhoWinsPoint)
                && match.getPoints1() == Points.WIN_THIRD_PITCH && match.getPoints2() == Points.ADVANTAGE )  {
            match.setPoints2(Points.WIN_THIRD_PITCH);
            updatePoints(match, playerWhoWinsPoint);
        } else if(Objects.equals(match.getName2(), playerWhoWinsPoint)
                && match.getPoints2() == Points.WIN_THIRD_PITCH && match.getPoints1() == Points.ADVANTAGE )  {
            match.setPoints1(Points.WIN_THIRD_PITCH);
            updatePoints(match, playerWhoWinsPoint);
        }  else {
            updatePoints(match, playerWhoWinsPoint);
        }
    }

    private void resetPoints(Match match) {
        match.setPoints1(0);
        match.setPoints2(0);
    }

    private void updatePoints(Match match, String playerWhoWinsPoint) {
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

    public Optional<String> getWinner(String uuid) {
        Match match = ongoingMatchesService.getMatch(uuid);
        return getWinner(match);
    }

    public Optional<String> getWinner(Match match) {
        if (match.getSet1() == 2 && match.getSet2() < 2) {
            return Optional.of(match.getName1());
        } else if (match.getSet2() == 2 && match.getSet1() < 2) {
            return Optional.of(match.getName2());
        } else {
            return Optional.empty();
        }
    }

}
