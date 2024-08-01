package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.Utils;
import com.sergeineretin.tennisScoreboard.dao.impl.MatchDaoImpl;
import com.sergeineretin.tennisScoreboard.dto.MatchDto;
import com.sergeineretin.tennisScoreboard.model.MatchScoreModel;
import com.sergeineretin.tennisScoreboard.model.Player;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class FindingMatchesByPlayerNameService {
    private final MatchDaoImpl matchScoreDao;
    private final ModelMapper modelMapper;
    private static FindingMatchesByPlayerNameService instance;

    public static synchronized FindingMatchesByPlayerNameService getInstance() {
        if (instance == null) {
            instance = new FindingMatchesByPlayerNameService();
        }
        return instance;
    }

    private FindingMatchesByPlayerNameService() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(MatchScoreModel.class)
                .addAnnotatedClass(Player.class)
                .buildSessionFactory();
        this.matchScoreDao = new MatchDaoImpl(factory);
        modelMapper = new ModelMapper();
    }

    public List<MatchDto> find(String name, Long page) {
        List<MatchScoreModel> byPlayerName = matchScoreDao.findByPlayerName(name, page);
        return byPlayerName.stream()
                .map(model -> modelMapper.map(model, MatchDto.class))
                .collect(Collectors.toList());
    }

    public long getNumberOfPages(String name) {
        long numberOfMatches = matchScoreDao.getNumberOfMatches(name);
        long numberOfPages = (long) Math.ceil((double) numberOfMatches / Utils.PAGE_SIZE);
        return Math.max(numberOfPages, 1);
    }
}
