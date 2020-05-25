package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
