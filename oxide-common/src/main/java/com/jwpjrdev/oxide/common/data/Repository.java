package com.jwpjrdev.oxide.common.data;

import com.jwpjrdev.oxide.common.data.types.Ban;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public abstract class Repository<T> {
    
    public abstract void save(T data);
    public abstract void delete(T data);
    public abstract void delete(UUID id);
    public abstract void deleteAll();
    public abstract CompletableFuture<T> getById(UUID id);
    public abstract CompletableFuture<List<T>> getAll();
}
