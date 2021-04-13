package com.jwpjrdev.oxide.common.data;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

public class OxideDAO<T> extends BasicDAO<T, String> {
    
    public OxideDAO(Class<T> entityClass, Datastore datastore) {
        super(entityClass, datastore);
    }
}
