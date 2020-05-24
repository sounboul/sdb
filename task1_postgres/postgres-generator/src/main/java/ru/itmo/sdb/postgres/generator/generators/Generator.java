package ru.itmo.sdb.postgres.generator.generators;

public interface Generator<T> {
    T generate();
}
