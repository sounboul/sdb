package ru.itmo.sdb.xmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itmo.sdb.xmongo.entities.Visit;

public interface Visits extends MongoRepository<Visit, Integer> {}
