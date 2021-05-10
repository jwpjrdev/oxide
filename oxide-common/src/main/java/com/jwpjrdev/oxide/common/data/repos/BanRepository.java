package com.jwpjrdev.oxide.common.data.repos;

import com.github.benmanes.caffeine.cache.Cache;
import com.jwpjrdev.oxide.common.DatabaseHandler;
import com.jwpjrdev.oxide.common.data.OxideDAO;
import com.jwpjrdev.oxide.common.data.Repository;
import com.jwpjrdev.oxide.common.data.interfaces.Moderatable;
import com.jwpjrdev.oxide.common.data.interfaces.PlayerControllable;
import com.jwpjrdev.oxide.common.data.types.Ban;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class BanRepository extends Repository<Ban>
        implements Moderatable<Ban>, PlayerControllable<Ban> {
    
    @NotNull private final DatabaseHandler databaseHandler;
    @NotNull private final OxideDAO<Ban> dao;
    
    @NotNull private final Cache<UUID, Ban> cache;
    
    // TODO: use DI for all repos
    public BanRepository(@NotNull DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
        this.dao = this.databaseHandler.getDAO("ban", Ban.class);
        System.out.println(this.dao);
        this.cache = this.dao.getCache();
        System.out.println("BanRepository:" + this.cache);
    }
    
    // TODO: save async and immediately update cache
    @Override
    public void save(@NotNull Ban data) {
        this.databaseHandler.getThreadPool().submit(() -> {
            this.dao.getCache().put(data.getId(), data);
            this.dao.save(data);
        });
    }
    
    public void delete(@NotNull Ban data) {
        this.databaseHandler.getThreadPool().submit(() -> {
           this.dao.getCache().invalidate(data.getId());
           this.dao.delete(data);
        });
    }
    
    public void delete(@NotNull UUID id) {
        this.databaseHandler.getThreadPool().submit(() -> {
            this.dao.getCache().invalidate(id);
            this.dao.deleteById(id.toString());
        });
    }
    
    // be very careful with this method; it can drop all your data.
    // TODO: perhaps make this method private? control access to it? or just let the end user deal with it.
    public void deleteAll() {
        this.databaseHandler.getThreadPool().submit(() -> {
            this.dao.getCache().invalidateAll();
            this.dao.getCollection().drop();
        });
    }
    
    // There might be a way to pull all the bans from the cache and then get the rest from the DB but idk
    public CompletableFuture<List<Ban>> getAll() {
        final CompletableFuture<List<Ban>> future = new CompletableFuture<>();
        this.databaseHandler.getThreadPool().submit(() -> {
            future.complete(this.dao
                    .find()
                    .asList()
            );
            return null;
        });
        return future;
    }
    
    public CompletableFuture<Ban> getById(UUID id) {
        final CompletableFuture<Ban> future = new CompletableFuture<>();
    
        Optional.ofNullable(this.cache.getIfPresent(id))
            .ifPresentOrElse(
                future::complete,
                () -> this.databaseHandler.getThreadPool().submit(
                    () -> future.complete(
                        Optional.ofNullable(
                            this.dao.createQuery()
                                .filter("id", id)
                                .get()
                        ).orElse(null)
                    )
                )
        );
        return future;
    }
    
    public CompletableFuture<List<Ban>> getAllForPlayer(UUID player) {
        final CompletableFuture<List<Ban>> future = new CompletableFuture<>();
        
        this.databaseHandler.getThreadPool().submit(() -> {
            future.complete(this.dao
                    .createQuery()
                    .filter("player", player)
                    // TODO: test
                    .filter("id in", this.dao.getCache().asMap().keySet())
                    .asList()
            );
            return null;
        });
        return future;
    }
    
    public CompletableFuture<List<Ban>> getAllByModerator(UUID moderator) {
        final CompletableFuture<List<Ban>> future = new CompletableFuture<>();
        this.databaseHandler.getThreadPool().submit(() -> {
            future.complete(this.dao
                    .createQuery()
                    .filter("moderator", moderator)
                    .asList()
            );
            return null;
        });
        return future;
    }
}
