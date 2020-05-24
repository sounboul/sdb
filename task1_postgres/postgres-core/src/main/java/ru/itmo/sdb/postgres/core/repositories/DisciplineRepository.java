package ru.itmo.sdb.postgres.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.postgres.core.models.Discipline;

@Repository
public interface DisciplineRepository extends CrudRepository<Discipline, Long> {
}
