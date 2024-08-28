package com.ericsson.retrospectivetool.repository;
import com.ericsson.retrospectivetool.model.TeamModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface TeamRepositoryInterface {

    Collection<TeamModel> getNodes();

    @Transactional
    int insertNode(TeamModel ps);

    @Transactional
    boolean deleteTeams();
}
