package com.sergeineretin.tennisScoreboard.dao.impl;

import com.sergeineretin.tennisScoreboard.dao.MatchScoreDao;
import com.sergeineretin.tennisScoreboard.model.MatchScoreModel;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@Slf4j
public class MatchScoreDaoImpl implements MatchScoreDao {
    private SessionFactory factory;
    public MatchScoreDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }
    @Override
    public Optional<MatchScoreModel> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<MatchScoreModel> findAll() {
        return List.of();
    }

    @Override
    public void save(MatchScoreModel match) {
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.info(e.getMessage());
        }
    }

    @Override
    public void delete(MatchScoreModel match) {

    }
}
