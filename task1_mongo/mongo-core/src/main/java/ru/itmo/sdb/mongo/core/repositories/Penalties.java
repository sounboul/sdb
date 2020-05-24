package ru.itmo.sdb.mongo.core.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itmo.sdb.mongo.core.entities.Penalty;

public interface Penalties extends MongoRepository<Penalty, Integer> {}
