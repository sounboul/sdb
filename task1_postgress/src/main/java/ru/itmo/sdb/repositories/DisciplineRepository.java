package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Discipline;

@Repository
public interface DisciplineRepository extends CrudRepository<Discipline, Long> {
}
