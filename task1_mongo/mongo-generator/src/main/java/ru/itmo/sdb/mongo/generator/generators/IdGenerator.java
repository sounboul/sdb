package ru.itmo.sdb.mongo.generator.generators;

import org.springframework.stereotype.Service;

@Service
public class IdGenerator {
    private int id = 0;

    public long generate() {
        return id++;
    }
}
