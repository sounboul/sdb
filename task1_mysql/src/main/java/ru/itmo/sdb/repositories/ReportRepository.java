package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.ReportEntity;

@Repository
public interface ReportRepository extends CrudRepository<ReportEntity, Long> {
}
