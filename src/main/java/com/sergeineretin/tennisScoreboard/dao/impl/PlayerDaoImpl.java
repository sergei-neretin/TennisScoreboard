package com.sergeineretin.tennisScoreboard.dao.impl;

import com.sergeineretin.tennisScoreboard.dao.PlayerDao;
import com.sergeineretin.tennisScoreboard.model.PlayerModel;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

@Slf4j
public class PlayerDaoImpl implements PlayerDao {
    private SessionFactory factory;
    public PlayerDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Optional<PlayerModel> findById(long id) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            PlayerModel playerModel = session.get(PlayerModel.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(playerModel);
        }
    }

    @Override
    public Optional<PlayerModel> findByName(String name) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from PlayerModel where name = :name", PlayerModel.class);
            query.setParameter("name", name);
            PlayerModel playerModel = (PlayerModel) query.getSingleResult();
            session.getTransaction().commit();
            return Optional.of(playerModel);
        } catch (NoResultException e) {
            return Optional.empty();
        }

    }

    @Override
    public List<PlayerModel> findAll() {
        return List.of();
    }

    @Override
    public void save(PlayerModel playerModel) {
        Transaction transaction = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(playerModel);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.info(e.getMessage());
        }
    }

    @Override
    public void delete(PlayerModel match) {

    }
}
