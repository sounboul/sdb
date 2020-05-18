package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.ReportAuthorsEntity;

@Repository
public interface ReportAuthorRepository extends CrudRepository<ReportAuthorsEntity, Long> {
}
