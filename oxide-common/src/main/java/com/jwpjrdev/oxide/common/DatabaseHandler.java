package com.jwpjrdev.oxide.common;

import com.jwpjrdev.oxide.common.data.OxideDAO;
import com.jwpjrdev.oxide.common.data.Repository;
import com.jwpjrdev.oxide.common.data.converters.UUIDConverter;
import com.jwpjrdev.oxide.common.data.repos.BanRepository;
import com.jwpjrdev.oxide.common.data.types.Ban;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.AccessLevel;
import lombok.Getter;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * To add a new data type, do the following:
 * 1. Create a new data class in the types subpackage.
 * 2. Create a new repository class in the repos subpackage.
 * 3. Add it to this class.
 */
@Getter
public class DatabaseHandler {
    
    private final MongoClient client;
    private final Morphia morphia;
    private final Datastore datastore;
    
    // TODO: rename this
    @Getter(AccessLevel.NONE)
    private final Map<String, OxideDAO<?>> daos;
    @Getter(AccessLevel.NONE)
    private final Map<String, Repository<?>> repositories;
    
    public DatabaseHandler(MongoCredential credential) {
        this("localhost", credential);
    }
    
    public DatabaseHandler(String host, MongoCredential credential) {
        this(host, 27017, credential);
    }
    
    public DatabaseHandler(String host, int port, MongoCredential credential) {
        ServerAddress address = new ServerAddress(host, port);
        final List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(credential);
        
        // TODO: this.client = new MongoClient(address, credentials);
        this.client = new MongoClient();
        this.morphia = new Morphia();
        
        this.morphia.getMapper().getConverters().addConverter(new UUIDConverter());
    
        this.daos = new HashMap<>();
        this.repositories = new HashMap<>();
        
        this.datastore = this.morphia.createDatastore(this.client, credential.getSource());
        this.datastore.ensureIndexes();
        
    }
    
    public <T> void addDAO(String key, OxideDAO<T> dao, Repository<T> repo, Class<T> type) {
        this.daos.put(key, dao);
        this.repositories.put(key, repo);
        this.morphia.map(type);
    }
    
    public <T> OxideDAO<T> getDAO(String key, Class<T> type) {
        return (OxideDAO<T>) this.daos.get(key);
    }
    
    public <T> Repository<T> getRepository(String key, Class<T> type) {
        return (Repository<T>) this.repositories.get(key);
    }
}
