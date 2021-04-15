package com.jwpjrdev.oxide.common.data.interfaces;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface PlayerControllable<T> {
    
    CompletableFuture<List<T>> getAllForPlayer(UUID player);
}
