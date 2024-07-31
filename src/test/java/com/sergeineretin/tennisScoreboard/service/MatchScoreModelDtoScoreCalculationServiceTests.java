package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.dto.Match;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatchScoreModelDtoScoreCalculationServiceTests {
    @Test
    public void game_with_score_40_40_is_not_end_if_player1_wins_a_point() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        Match match = MatchTestUtils.getMatch();
        match.setPoints1(40);
        match.setPoints2(40);
        int game1 = match.getGame1();

        underTest.updateScore(match, match.getName1());

        assertEquals(game1, match.getGame1());
    }

    @Test
    public void game_with_score_40_40_is_not_end_if_player2_wins_a_point() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        Match match = MatchTestUtils.getMatch();
        match.setPoints1(40);
        match.setPoints2(40);

        int game2 = match.getGame2();

        underTest.updateScore(match, match.getName2());

        assertEquals(game2, match.getGame2());
    }

    @Test
    public void player1_who_has_the_advantage_and_wins_the_next_service_wins_the_game() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        Match match = MatchTestUtils.getMatch();
        match.setPoints1(40);
        match.setPoints2(40);
        int game1 = match.getGame1();

        underTest.updateScore(match, match.getName1());
        underTest.updateScore(match, match.getName1());

        assertEquals(game1 + 1, match.getGame1());
    }

    @Test
    public void player2_who_has_the_advantage_and_wins_the_next_service_wins_the_game() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        Match match = MatchTestUtils.getMatch();
        match.setPoints1(40);
        match.setPoints2(40);
        int game2 = match.getGame2();

        underTest.updateScore(match, match.getName2());
        underTest.updateScore(match, match.getName2());

        assertEquals(game2 + 1, match.getGame2());
    }

    @Test
    public void player1_who_has_the_advantage_and_lose_the_next_service_lose_the_advantage() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        Match match = MatchTestUtils.getMatch();
        match.setPoints1(40);
        match.setPoints2(40);

        int game1 = match.getGame1();
        int points1 = match.getPoints1();

        underTest.updateScore(match, match.getName1());
        underTest.updateScore(match, match.getName2());

        assertEquals(points1, match.getPoints1());
        assertEquals(game1, match.getGame1());
    }

    @Test
    public void player2_who_has_the_advantage_and_lose_the_next_service_lose_the_advantage() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        Match match = MatchTestUtils.getMatch();
        match.setPoints1(40);
        match.setPoints2(40);

        int game2 = match.getGame2();
        int points2 = match.getPoints2();

        underTest.updateScore(match, match.getName2());
        underTest.updateScore(match, match.getName1());

        assertEquals(points2, match.getPoints2());
        assertEquals(game2, match.getGame2());
    }

    @Test
    public void game_with_score_40_0_is_end_if_player1_wins_a_point() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        Match match = MatchTestUtils.getMatch();
        match.setPoints1(40);
        match.setPoints2(0);
        int game1 = match.getGame1();

        underTest.updateScore(match, match.getName1());

        assertEquals(game1 + 1, match.getGame1());
    }

    @Test
    public void game_with_score_40_0_is_end_if_player2_wins_a_point() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        Match match = MatchTestUtils.getMatch();
        match.setPoints1(0);
        match.setPoints2(40);
        int game2 = match.getGame2();

        underTest.updateScore(match, match.getName2());

        assertEquals(game2 + 1, match.getGame2());
    }

    @Test
    public void set_with_score_games_6_6_is_leads_to_a_tiebreak() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        Match match = MatchTestUtils.getMatch();
        match.setGame1(6);
        match.setGame2(6);

        underTest.updateScore(match, match.getName1());
        underTest.updateScore(match, match.getName2());

        assertEquals(1, match.getPoints1());
        assertEquals(1, match.getPoints2());
    }

    @Test
    public void if_player1_score_games_becomes_7_5_the_set_ends() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        Match match = MatchTestUtils.getMatch();
        match.setGame1(6);
        match.setGame2(5);
        match.setPoints1(40);
        int set1 = match.getSet1();

        underTest.updateScore(match, match.getName1());

        assertEquals(set1 + 1, match.getSet1());
    }

    @Test
    public void if_player2_score_games_becomes_7_5_the_set_ends() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        Match match = MatchTestUtils.getMatch();
        match.setGame2(6);
        match.setGame1(5);
        match.setPoints2(40);
        int set2 = match.getSet2();

        underTest.updateScore(match, match.getName2());

        assertEquals(set2 + 1, match.getSet2());
    }

    @Test
    public void the_first_to_score_7_points_with_a_2_point_difference_wins_tiebreak() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        Match match = MatchTestUtils.getMatch();
        match.setGame1(6);
        match.setGame2(6);
        match.setPoints1(6);
        match.setPoints2(5);

        int set1 = match.getSet1();

        underTest.updateScore(match, match.getName1());

        assertEquals(set1 + 1, match.getSet1());
    }

    @Test
    public void the_second_to_score_7_points_with_a_2_point_difference_wins_tiebreak() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        Match match = MatchTestUtils.getMatch();
        match.setGame1(6);
        match.setGame2(6);
        match.setPoints2(6);
        match.setPoints1(5);
        int set2 = match.getSet2();

        underTest.updateScore(match, match.getName2());

        assertEquals(set2 + 1, match.getSet2());
    }

    @Test
    public void if_score_6_6_points_reset_tiebreak() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        Match match = MatchTestUtils.getMatch();
        match.setGame1(6);
        match.setGame2(6);
        match.setPoints1(6);
        match.setPoints2(5);

        int set1 = match.getSet1();
        underTest.updateScore(match, match.getName2());

        assertEquals(0, match.getPoints1());
        assertEquals(0, match.getPoints2());
        assertEquals(set1, match.getSet1());
    }

    @Test
    public void if_player1_wins_two_sets_then_wins_the_match() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        Match match = MatchTestUtils.getMatch();
        match.setSet1(2);

        assertTrue(underTest.getWinner(match).isPresent());
        assertEquals(underTest.getWinner(match).get(), match.getName1());
    }

    @Test
    public void if_player2_wins_two_sets_then_wins_the_match() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        Match match = MatchTestUtils.getMatch();
        match.setSet2(2);

        assertTrue(underTest.getWinner(match).isPresent());
        assertEquals(underTest.getWinner(match).get(), match.getName2());
    }
}
