package com.sergeineretin.tennisScoreboard.dao.impl;

import com.sergeineretin.tennisScoreboard.dao.MatchDao;
import com.sergeineretin.tennisScoreboard.model.MatchScoreModel;
import com.sergeineretin.tennisScoreboard.model.Player;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaPredicate;


import java.util.List;
import java.util.Optional;

@Slf4j
public class MatchDaoImpl implements MatchDao {
    private SessionFactory factory;
    public MatchDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }
    @Override
    public Optional<MatchScoreModel> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<MatchScoreModel> findByPlayerName(String name) {
        try(Session session = factory.openSession()) {
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<MatchScoreModel> query = cb.createQuery(MatchScoreModel.class);
            Root<MatchScoreModel> root = query.from(MatchScoreModel.class);
            root.fetch("player1", JoinType.LEFT);
            root.fetch("player2", JoinType.LEFT);
            JpaPredicate likePlayer1Name = cb.like(root.get("player1").get("name"), wrapToLike(name));
            JpaPredicate likePlayer2Name = cb.like(root.get("player2").get("name"), wrapToLike(name));
            query.where(cb.or(likePlayer1Name, likePlayer2Name));
            return session.createQuery(query).getResultList();

        }
    }

    private String wrapToLike(String value) {

        return value == null ? "%" : "%" + value + "%";
    }

    @Override
    public List<MatchScoreModel> findAll() {
        return List.of();
    }

    @Override
    public void save(MatchScoreModel matchScoreModel) {
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(matchScoreModel);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.info(e.getMessage());
        }
    }

    @Override
    public void delete(MatchScoreModel matchScoreModel) {

    }
}
