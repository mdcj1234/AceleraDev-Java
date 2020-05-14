package com.challenge.repository.interfaces;

public interface RepositoryInterface<T> {
    T save(T obj);
}