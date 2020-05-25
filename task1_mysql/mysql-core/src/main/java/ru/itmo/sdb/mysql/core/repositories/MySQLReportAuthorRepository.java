package ru.itmo.sdb.mysql.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.mysql.core.models.ReportAuthorsEntity;

@Repository
public interface MySQLReportAuthorRepository extends CrudRepository<ReportAuthorsEntity, Long> {
    Iterable<ReportAuthorsEntity> findByReportId(long reportId);
}
