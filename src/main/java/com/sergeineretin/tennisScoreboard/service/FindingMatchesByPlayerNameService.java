package com.sergeineretin.tennisScoreboard.service;

import com.sergeineretin.tennisScoreboard.dao.impl.MatchDaoImpl;
import com.sergeineretin.tennisScoreboard.dto.MatchScoreDto;
import com.sergeineretin.tennisScoreboard.model.MatchScoreModel;
import com.sergeineretin.tennisScoreboard.model.Player;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;

import java.util.List;

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

    public List<MatchScoreDto> find(String name) {
        List<MatchScoreModel> byPlayerName = matchScoreDao.findByPlayerName(name);
        return byPlayerName.stream()
                .map(model -> modelMapper.map(model, MatchScoreDto.class))
                .toList();
    }
}
