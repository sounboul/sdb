package ru.itmo.sdb.mysql.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.mysql.core.models.ReportAuthorsEntity;

@Repository
public interface ReportAuthorRepository extends CrudRepository<ReportAuthorsEntity, Long> {
}
