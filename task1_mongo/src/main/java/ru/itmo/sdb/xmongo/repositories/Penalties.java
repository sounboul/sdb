package ru.itmo.sdb.xmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itmo.sdb.xmongo.entities.Penalty;

public interface Penalties extends MongoRepository<Penalty, Integer> {}
