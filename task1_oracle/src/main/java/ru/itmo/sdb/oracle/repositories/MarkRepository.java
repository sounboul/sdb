package ru.itmo.sdb.oracle.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.oracle.models.Mark;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {
}
