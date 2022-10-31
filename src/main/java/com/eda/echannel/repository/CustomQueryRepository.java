package com.eda.echannel.repository;

import com.eda.echannel.model.Channel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
@Transactional(readOnly = true)
public class CustomQueryRepository<T> {

    @PersistenceContext
    EntityManager entityManager;

    public List<Channel> getChannelList(String searchString) {
        String sql = "select * from channel where " + searchString;
        Query query = entityManager.createNativeQuery(sql, Channel.class);
        List<Channel> channelList = query.getResultList();
        return channelList;
    }

}
