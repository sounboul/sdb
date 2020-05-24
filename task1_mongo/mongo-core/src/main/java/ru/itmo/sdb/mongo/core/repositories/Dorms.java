package ru.itmo.sdb.mongo.core.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itmo.sdb.mongo.core.entities.Dormitory;

public interface Dorms extends MongoRepository<Dormitory, Integer> {}
