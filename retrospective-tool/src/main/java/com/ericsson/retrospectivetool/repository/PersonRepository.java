package com.ericsson.retrospectivetool.repository;

import com.ericsson.retrospectivetool.model.PersonModel;
import com.ericsson.retrospectivetool.model.TeamModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
public class PersonRepository implements PersonRepositoryInterface {
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public Collection<PersonModel> getMembers() {
        String jpql = "select p from PersonModel p";
        TypedQuery<PersonModel> query = entityManager.createQuery(jpql, PersonModel.class);
        return query.getResultList();
    }
    @Override
    public PersonModel getMember(int id) {
        return entityManager.find(PersonModel.class, id);
    }

    @Override
    @Transactional
    public int insertMember(PersonModel ps) {
        entityManager.persist(ps);
        entityManager.flush();

        return ps.getId();
    }

    @Override
    @Transactional
    public boolean deleteMembers() {
        Query query = entityManager.createQuery("delete from PersonModel ");
        query.executeUpdate();
        return true;
    }
}

