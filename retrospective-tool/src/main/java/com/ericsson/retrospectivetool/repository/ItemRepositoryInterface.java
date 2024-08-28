package com.ericsson.retrospectivetool.repository;

import com.ericsson.retrospectivetool.model.ItemCommentModel;
import com.ericsson.retrospectivetool.model.ItemModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface ItemRepositoryInterface {
    Collection<ItemModel> getNodes();

    @Transactional
    int insertNode(ItemModel ps);

    @Transactional
    int insertComment(ItemCommentModel ic);

    Collection<ItemModel> getSad();

    Collection<ItemModel> getMad();

    Collection<ItemModel> getGlad();

    Collection<ItemCommentModel> getCommentSad();

    Collection<ItemCommentModel> getCommentGlad();

    Collection<ItemCommentModel> getCommentMad();


    @Transactional
    boolean deleteMoods();
}
