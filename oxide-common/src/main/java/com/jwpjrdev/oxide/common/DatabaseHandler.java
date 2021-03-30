package com.jwpjrdev.oxide.common;

import com.jwpjrdev.oxide.common.data.Ban;
import com.jwpjrdev.oxide.common.data.dao.BanDAO;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    
    private MongoClient client;
    private Morphia morphia;
    
    private Datastore datastore;
    private BanDAO banDAO;
    
    public DatabaseHandler(String host, String database, int port, MongoCredential credential) {
        ServerAddress address = new ServerAddress("localhost");
        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(credential);
        
        client = new MongoClient(address, credentials);
        morphia = new Morphia();
        
        morphia.map(Ban.class);
        
        datastore = morphia.createDatastore(client, database);
        datastore.ensureIndexes();
        
        banDAO = new BanDAO(Ban.class, datastore);
    }
}
