package com.sergeineretin.tennisScoreboard.dao.impl;

import com.sergeineretin.tennisScoreboard.dao.MatchDao;
import com.sergeineretin.tennisScoreboard.model.MatchScoreModel;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;


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

    public List<MatchScoreModel> findByName(String name) {
        try(Session session = factory.openSession()) {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<MatchScoreModel> cr = criteriaBuilder.createQuery(MatchScoreModel.class);
            Root<MatchScoreModel> root = cr.from(MatchScoreModel.class);
            cr.select(root);

            Query<MatchScoreModel> query = session.createQuery(cr);
            return query.getResultList();
        }
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
