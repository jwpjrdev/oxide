package com.jwpjrdev.oxide.common.data.repos;

import com.jwpjrdev.oxide.common.DatabaseHandler;
import com.jwpjrdev.oxide.common.data.OxideDAO;
import com.jwpjrdev.oxide.common.data.Repository;
import com.jwpjrdev.oxide.common.data.interfaces.Moderatable;
import com.jwpjrdev.oxide.common.data.types.Ban;

import java.util.List;
import java.util.UUID;

public class BanRepository extends Repository<Ban> implements Moderatable {
    
    private final DatabaseHandler databaseHandler;
    private final OxideDAO<Ban> dao;
    
    // TODO: use DI for all repos
    public BanRepository(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
        this.dao = this.databaseHandler.getDAO("ban", Ban.class);
    }
    
    @Override
    public void save(Ban data) {
        // TODO: I'm sure there's a better way to do this. Perhaps get the DatabaseHandler instance via DI.
        this.dao.save(data);
    }
    
    @Override
    public List<Ban> getAll() {
        return this.dao
                .find()
                .asList();
    }
    
    // @Nullable
    public Ban getById(UUID id) {
        return this.dao
                .createQuery()
                .filter("id", id)
                .get();
    }
    
    public List<Ban> getAllForPlayer(UUID player) {
        return this.dao
                .createQuery()
                .filter("player", player)
                .asList();
    }
    
    public List<Ban> getAllByModerator(UUID moderator) {
        return this.dao
                .createQuery()
                .filter("moderator", moderator)
                .asList();
    }
}
