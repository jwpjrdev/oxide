package com.jwpjrdev.oxide.common.data;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Getter
public class OxideDAO<T> extends BasicDAO<T, String> {
    
    private final Cache<@NotNull UUID, @NotNull T> cache;
    
    public OxideDAO(Class<T> entityClass, Datastore datastore) {
        // TODO: config for Caffeine because some users may want to configure it
        this(
                entityClass,
                datastore,
                Caffeine.newBuilder()
                    .maximumSize(10_000)
                    .expireAfterAccess(5, TimeUnit.MINUTES)
                    .build()
        );
    }
    
    public OxideDAO(@NotNull Class<T> entityClass, @NotNull Datastore datastore, Cache<@NotNull UUID, @NotNull T> cache) {
        super(entityClass, datastore);
        this.cache = cache;
    }
}
