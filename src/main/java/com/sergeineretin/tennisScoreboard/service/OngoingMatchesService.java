package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.dto.Match;
import com.sergeineretin.tennisScoreboard.dto.Player;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.UUID;

@Slf4j
public class OngoingMatchesService {
    private static OngoingMatchesService instance;
    private HashMap<UUID, Match> ongoingMatches;
    private PlayerService playerService;

    private OngoingMatchesService() {
        ongoingMatches = new HashMap<>();
        playerService = PlayerService.getInstance();
    }

    public static synchronized OngoingMatchesService getInstance() {
        if (instance == null) {
            instance = new OngoingMatchesService();
        }
        return instance;
    }

    public String add(String name1, String name2) {
        UUID uuid = UUID.randomUUID();
        Player player1 = playerService.getPlayer(name1);
        Player player2 = playerService.getPlayer(name2);
        Match match = Match.builder()
                        .id1(player1.getId())
                        .id2(player2.getId())
                        .build();
        ongoingMatches.put(uuid, match);
        log.info(MessageFormat.format("Added new ongoing match: UUID = {0}, player1 = {1}, player2 = {2}.",
                uuid,
                match.getId1(),
                match.getId2()));

        return uuid.toString();
    }

    public Match getMatch(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        return ongoingMatches.get(uuid);
    }

    public void remove(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        ongoingMatches.remove(uuid);
    }
}
