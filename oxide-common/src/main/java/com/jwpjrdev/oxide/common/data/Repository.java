package com.jwpjrdev.oxide.common.data;

import java.util.List;

public abstract class Repository<T> {

    public abstract void save(T data);
    public abstract List<T> getAll();
}
