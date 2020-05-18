package ru.itmo.sdb.xmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itmo.sdb.xmongo.entities.Dormitory;

public interface Dorms extends MongoRepository<Dormitory, Integer> {}
