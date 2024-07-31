package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.dto.Match;
import com.sergeineretin.tennisScoreboard.exceptions.MatchNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class OngoingMatchesService {
    private static OngoingMatchesService instance;
    private ConcurrentHashMap<UUID, Match> ongoingMatches;

    private OngoingMatchesService() {
        ongoingMatches = new ConcurrentHashMap<>();
    }

    public static synchronized OngoingMatchesService getInstance() {
        if (instance == null) {
            instance = new OngoingMatchesService();
        }
        return instance;
    }

    public String add(String name1, String name2) {
        UUID uuid = UUID.randomUUID();
        Match match = Match.builder().name1(name1).name2(name2).build();
        ongoingMatches.put(uuid, match);
        log.info(MessageFormat.format("Added new ongoing match: {0}",
                match));

        return uuid.toString();
    }

    public Match getMatch(String uuidString) {
        try {
            UUID uuid = UUID.fromString(uuidString);
            Match match = ongoingMatches.get(uuid);
            if (match == null) {
                throw new IllegalArgumentException();
            } else {
                return match;
            }
        } catch (IllegalArgumentException e) {
            log.info("Match not found.");
            throw new MatchNotFoundException("Match not found.");
        }
    }


    public void remove(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        ongoingMatches.remove(uuid);
    }
}
