package com.sergeineretin.tennisScoreboard.dao.impl;

import com.sergeineretin.tennisScoreboard.dao.PlayerDao;
import com.sergeineretin.tennisScoreboard.model.MatchScoreModel;
import com.sergeineretin.tennisScoreboard.model.Player;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
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
public class PlayerDaoImpl implements PlayerDao {
    private SessionFactory factory;
    public PlayerDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<Player> findById(long id) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            Player player = session.get(Player.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(player);
        }
    }

    @Override
    public Optional<Player> findByName(String name) {
        try(Session session = factory.openSession()) {
            HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Player> cr = cb.createQuery(Player.class);
            Root<Player> root = cr.from(Player.class);
            cr.select(root).where(cb.equal(root.get("name"), name));

            Query<Player> query = session.createQuery(cr);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Player> findAll() {
        return List.of();
    }

    @Override
    public void save(Player player) {
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.info(e.getMessage());
        }
    }

    @Override
    public void delete(Player match) {

    }
}
