package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Mark;
import ru.itmo.sdb.models.MigrationDuplicateRecord;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {
}
