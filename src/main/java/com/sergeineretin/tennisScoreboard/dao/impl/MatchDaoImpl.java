package com.sergeineretin.tennisScoreboard.dao.impl;

import com.sergeineretin.tennisScoreboard.Utils;
import com.sergeineretin.tennisScoreboard.dao.MatchDao;
import com.sergeineretin.tennisScoreboard.model.MatchScoreModel;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


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
    public List<MatchScoreModel> findByPlayerName(String name, long page) {
        try(Session session = factory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<MatchScoreModel> query = cb.createQuery(MatchScoreModel.class);
            Root<MatchScoreModel> root = query.from(MatchScoreModel.class);
            root.fetch("player1", JoinType.LEFT);
            root.fetch("player2", JoinType.LEFT);
            Predicate likePlayer1Name = cb.like(root.get("player1").get("name"), wrapToLike(name));
            Predicate likePlayer2Name = cb.like(root.get("player2").get("name"), wrapToLike(name));
            query.where(cb.or(likePlayer1Name, likePlayer2Name));
            query.orderBy(cb.asc(root.get("id")));

            TypedQuery<MatchScoreModel> typedQuery = session.createQuery(query);

            typedQuery.setFirstResult((int)(page-1) * Utils.PAGE_SIZE);
            typedQuery.setMaxResults(Utils.PAGE_SIZE);
            System.out.println((page - 1) * Utils.PAGE_SIZE);

            return typedQuery.getResultList();
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

    public long getNumberOfMatches(String name) {
        try (Session session = factory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<MatchScoreModel> root = query.from(MatchScoreModel.class);
            Predicate likePlayer1Name = cb.like(root.get("player1").get("name"), wrapToLike(name));
            Predicate likePlayer2Name = cb.like(root.get("player2").get("name"), wrapToLike(name));
            query.where(cb.or(likePlayer1Name, likePlayer2Name));
            query.select(cb.count(root));
            TypedQuery<Long> typedQuery = session.createQuery(query);
            return typedQuery.getSingleResult();
        }
    }
}
