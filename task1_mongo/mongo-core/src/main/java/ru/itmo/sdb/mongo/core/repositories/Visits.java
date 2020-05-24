package ru.itmo.sdb.mongo.core.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itmo.sdb.mongo.core.entities.Visit;

public interface Visits extends MongoRepository<Visit, Integer> {}
