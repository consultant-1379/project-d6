package com.ericsson.retrospectivetool.repository;

import com.ericsson.retrospectivetool.model.PersonModel;
import com.ericsson.retrospectivetool.model.TeamModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface PersonRepositoryInterface {

    Collection<PersonModel> getMembers();

    PersonModel getMember(int id);
    @Transactional
    int insertMember(PersonModel ps);

    @Transactional
    boolean deleteMembers();
}
