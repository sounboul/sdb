package ru.itmo.sdb.xmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itmo.sdb.xmongo.entities.Room;

public interface Rooms extends MongoRepository<Room, Integer> {}
