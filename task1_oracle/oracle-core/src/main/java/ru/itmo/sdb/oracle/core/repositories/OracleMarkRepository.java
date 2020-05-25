package ru.itmo.sdb.oracle.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.oracle.core.models.Mark;

@Repository
public interface OracleMarkRepository extends CrudRepository<Mark, Long> {
}
