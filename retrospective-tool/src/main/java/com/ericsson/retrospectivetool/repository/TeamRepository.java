package com.ericsson.retrospectivetool.repository;

import com.ericsson.retrospectivetool.model.TeamModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
public class TeamRepository implements TeamRepositoryInterface {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public Collection<TeamModel> getNodes() {
        String jpql = "select p from TeamModel p";
        TypedQuery<TeamModel> query = entityManager.createQuery(jpql, TeamModel.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public int insertNode(TeamModel ps) {
        entityManager.persist(ps);
        entityManager.flush();

        return ps.getId();
    }

    @Override
    @Transactional
    public boolean deleteTeams() {
        Query query = entityManager.createQuery("delete from TeamModel ");
        query.executeUpdate();
        return true;
    }

}
