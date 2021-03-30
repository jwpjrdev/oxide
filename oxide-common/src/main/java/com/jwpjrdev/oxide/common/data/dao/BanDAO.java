package com.jwpjrdev.oxide.common.data.dao;

import com.jwpjrdev.oxide.common.data.Ban;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

public class BanDAO extends BasicDAO<Ban, String> {

    public BanDAO(Class<Ban> entityClass, Datastore datastore) {
        super(entityClass, datastore);
    }
}
