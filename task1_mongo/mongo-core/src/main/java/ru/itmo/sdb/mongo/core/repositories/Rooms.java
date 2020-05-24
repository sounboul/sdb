package ru.itmo.sdb.mongo.core.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itmo.sdb.mongo.core.entities.Room;

public interface Rooms extends MongoRepository<Room, Integer> {}
