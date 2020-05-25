package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.MigrationDuplicateRecord;

@Repository
public interface MigrationDuplicateRecordRepository extends CrudRepository<MigrationDuplicateRecord, Long> {
}
