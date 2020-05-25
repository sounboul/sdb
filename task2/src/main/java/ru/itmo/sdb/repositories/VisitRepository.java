package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Visit;

@Repository
public interface VisitRepository extends CrudRepository<Visit, Long> {
}
