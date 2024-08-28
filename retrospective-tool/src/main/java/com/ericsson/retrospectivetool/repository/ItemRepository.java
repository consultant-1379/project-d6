package com.ericsson.retrospectivetool.repository;

import com.ericsson.retrospectivetool.model.ItemCommentModel;
import com.ericsson.retrospectivetool.model.ItemModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
public class ItemRepository implements ItemRepositoryInterface{
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public Collection<ItemModel> getNodes() {
        String jpql = "select p from ItemModel p";
        TypedQuery<ItemModel> query = entityManager.createQuery(jpql, ItemModel.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public int insertNode(ItemModel ps) {
        entityManager.persist(ps);
        entityManager.flush();
        return ps.getId();
    }

    @Override
    @Transactional
    public int insertComment(ItemCommentModel ic) {
        entityManager.persist(ic);
        entityManager.flush();
        return ic.getId();
    }

    @Override
    public Collection<ItemCommentModel> getCommentGlad() {
        String item = "Glad";
        String moodJpql = "SELECT p from ItemCommentModel p WHERE p.mood = '" + item + "'";
        TypedQuery<ItemCommentModel> query = entityManager.createQuery(moodJpql, ItemCommentModel.class);
        return query.getResultList();
    }

    @Override
    public Collection<ItemCommentModel> getCommentSad() {
        String item = "Sad";
        String moodJpql = "SELECT p from ItemCommentModel p WHERE p.mood = '" + item + "'";
        TypedQuery<ItemCommentModel> query = entityManager.createQuery(moodJpql, ItemCommentModel.class);
        return query.getResultList();
    }

    @Override
    public Collection<ItemCommentModel> getCommentMad() {
        String item = "Mad";
        String moodJpql = "SELECT p from ItemCommentModel p WHERE p.mood = '" + item + "'";
        TypedQuery<ItemCommentModel> query = entityManager.createQuery(moodJpql, ItemCommentModel.class);
        return query.getResultList();
    }

    @Override
    public Collection<ItemModel> getSad() {
        String mood = "Sad";
        String moodJpql = "SELECT p from ItemModel p WHERE p.mood = '" + mood + "' ORDER BY p.priority desc";
        TypedQuery<ItemModel> query = entityManager.createQuery(moodJpql, ItemModel.class);
        return query.getResultList();
    }

    @Override
    public Collection<ItemModel> getMad() {
        String mood = "Mad";
        String moodJpql = "SELECT p from ItemModel p WHERE p.mood = '" + mood + "' ORDER BY p.priority desc";
        TypedQuery<ItemModel> query = entityManager.createQuery(moodJpql, ItemModel.class);
        return query.getResultList();
    }

    @Override
    public Collection<ItemModel> getGlad() {
        String mood = "Glad";
        String moodJpql = "SELECT p from ItemModel p WHERE p.mood = '" + mood + "' ORDER BY p.priority desc";
        TypedQuery<ItemModel> query = entityManager.createQuery(moodJpql, ItemModel.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public boolean deleteMoods() {
        Query query = entityManager.createQuery("delete from ItemModel ");
        Query query2 = entityManager.createQuery("delete from ItemCommentModel ");
        query.executeUpdate();
        query2.executeUpdate();
        return true;
    }
}
