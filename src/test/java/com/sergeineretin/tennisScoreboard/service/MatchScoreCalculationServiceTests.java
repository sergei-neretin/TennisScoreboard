package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.dto.Match;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatchScoreCalculationServiceTests {
    @Test
    public void game_with_score_40_40_is_not_end_if_player1_wins_a_point() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        long firstPlayerId = 1;
        Match match = Match.builder().id1(firstPlayerId).points1(40).points2(40).build();
        int game1 = match.getGame1();

        underTest.updateScore(match, firstPlayerId);

        assertEquals(game1, match.getGame1());
    }

    @Test
    public void game_with_score_40_40_is_not_end_if_player2_wins_a_point() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        long secondPlayerId = 1;
        Match match = Match.builder().id2(secondPlayerId).points1(40).points2(40).build();
        int game2 = match.getGame2();

        underTest.updateScore(match, secondPlayerId);

        assertEquals(game2, match.getGame2());
    }

    @Test
    public void player1_who_has_the_advantage_and_wins_the_next_service_wins_the_game() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        long firstPlayerId = 1;
        Match match = Match.builder().id1(firstPlayerId).points1(40).points2(40).build();
        int game1 = match.getGame1();

        underTest.updateScore(match, firstPlayerId);
        underTest.updateScore(match, firstPlayerId);

        assertEquals(game1 + 1, match.getGame1());
    }

    @Test
    public void player2_who_has_the_advantage_and_wins_the_next_service_wins_the_game() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        long secondPlayerId = 1;
        Match match = Match.builder().id2(secondPlayerId).points1(40).points2(40).build();
        int game2 = match.getGame2();

        underTest.updateScore(match, secondPlayerId);
        underTest.updateScore(match, secondPlayerId);

        assertEquals(game2 + 1, match.getGame2());
    }

    @Test
    public void player1_who_has_the_advantage_and_lose_the_next_service_lose_the_advantage() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        long firstPlayerId = 1;
        long secondPlayerId = 2;
        Match match = Match.builder().id1(firstPlayerId).id2(secondPlayerId).points1(40).points2(40).build();
        int game1 = match.getGame1();
        int points1 = match.getPoints1();

        underTest.updateScore(match, firstPlayerId);
        underTest.updateScore(match, secondPlayerId);

        assertEquals(points1, match.getPoints1());
        assertEquals(game1, match.getGame1());
    }

    @Test
    public void player2_who_has_the_advantage_and_lose_the_next_service_lose_the_advantage() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        long secondPlayerId = 1;
        long firstPlayerId = 2;
        Match match = Match.builder().id2(secondPlayerId).id1(firstPlayerId).points1(40).points2(40).build();
        int game2 = match.getGame2();
        int points2 = match.getPoints2();

        underTest.updateScore(match, secondPlayerId);
        underTest.updateScore(match, firstPlayerId);

        assertEquals(points2, match.getPoints2());
        assertEquals(game2, match.getGame2());
    }

    @Test
    public void game_with_score_40_0_is_end_if_player1_wins_a_point() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        long firstPlayerId = 1;
        Match match = Match.builder().id1(firstPlayerId).points1(40).points2(0).build();
        int game1 = match.getGame1();

        underTest.updateScore(match, firstPlayerId);

        assertEquals(game1 + 1, match.getGame1());
    }

    @Test
    public void game_with_score_40_0_is_end_if_player2_wins_a_point() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        long secondPlayerId = 1;
        Match match = Match.builder().id2(secondPlayerId).points2(40).points1(0).build();
        int game2 = match.getGame2();

        underTest.updateScore(match, secondPlayerId);

        assertEquals(game2 + 1, match.getGame2());
    }

    @Test
    public void set_with_score_games_6_6_is_leads_to_a_tiebreak() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        long firstPlayerId = 1;
        long secondPlayerId = 2;

        Match match = Match.builder().id1(firstPlayerId).game1(6).game2(6).build();

        underTest.updateScore(match, firstPlayerId);
        underTest.updateScore(match, secondPlayerId);

        assertEquals(1, match.getPoints1());
        assertEquals(1, match.getPoints2());
    }

    @Test
    public void if_player1_score_games_becomes_7_5_the_set_ends() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        long firstPlayerId = 1;
        Match match = Match.builder()
                .id1(firstPlayerId)
                .game1(6)
                .game2(5)
                .points1(40)
                .build();
        int set1 = match.getSet1();

        underTest.updateScore(match, firstPlayerId);

        assertEquals(set1 + 1, match.getSet1());
    }

    @Test
    public void if_player2_score_games_becomes_7_5_the_set_ends() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        long secondPlayerId = 1;
        Match match = Match.builder()
                .id2(secondPlayerId)
                .game2(6)
                .game1(5)
                .points2(40)
                .build();
        int set2 = match.getSet2();

        underTest.updateScore(match, secondPlayerId);

        assertEquals(set2 + 1, match.getSet2());
    }

    @Test
    public void the_first_to_score_7_points_with_a_2_point_difference_wins_tiebreak() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        long firstPlayerId = 1;
        Match match = Match.builder()
                .id1(firstPlayerId)
                .game1(6)
                .game2(6)
                .points1(6)
                .points2(5)
                .build();
        int set1 = match.getSet1();

        underTest.updateScore(match, firstPlayerId);

        assertEquals(set1 + 1, match.getSet1());
    }

    @Test
    public void the_second_to_score_7_points_with_a_2_point_difference_wins_tiebreak() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        long secondPlayerId = 1;
        Match match = Match.builder()
                .id2(secondPlayerId)
                .game2(6)
                .game1(6)
                .points2(6)
                .points1(5)
                .build();
        int set2 = match.getSet2();

        underTest.updateScore(match, secondPlayerId);

        assertEquals(set2 + 1, match.getSet2());
    }

    @Test
    public void if_score_6_6_points_reset_tiebreak() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        long firstPlayerId = 1;
        Match match = Match.builder()
                .id1(firstPlayerId)
                .game1(6)
                .game2(6)
                .points1(5)
                .points2(6)
                .build();
        int set1 = match.getSet1();
        underTest.updateScore(match, firstPlayerId);

        assertEquals(0, match.getPoints1());
        assertEquals(0, match.getPoints2());
        assertEquals(set1, match.getSet1());
    }

    @Test
    public void if_player1_wins_two_sets_then_wins_the_match() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        long firstPlayerId = 1;
        Match match = Match.builder()
                .id1(firstPlayerId)
                .set1(2)
                .build();

        assertTrue(underTest.getWinner(match).isPresent());
        assertEquals(underTest.getWinner(match).get(), firstPlayerId);
    }

    @Test
    public void if_player2_wins_two_sets_then_wins_the_match() {
        MatchScoreCalculationService underTest = new MatchScoreCalculationService();
        long secondPlayerId = 1;
        Match match = Match.builder()
                .id2(secondPlayerId)
                .set2(2)
                .build();

        assertTrue(underTest.getWinner(match).isPresent());
        assertEquals(underTest.getWinner(match).get(), secondPlayerId);
    }
}
