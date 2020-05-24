package ru.itmo.sdb.oracle.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.oracle.models.Direction;

@Repository
public interface DirectionRepository extends CrudRepository<Direction, Long> {
}
