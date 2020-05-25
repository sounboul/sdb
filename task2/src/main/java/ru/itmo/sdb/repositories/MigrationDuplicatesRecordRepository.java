package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.MigrationDuplicatesRecord;

@Repository
public interface MigrationDuplicatesRecordRepository extends CrudRepository<MigrationDuplicatesRecord, Long> {
}
