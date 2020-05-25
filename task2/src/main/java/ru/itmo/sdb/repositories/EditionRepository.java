package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Edition;
import ru.itmo.sdb.models.Mark;

@Repository
public interface EditionRepository extends CrudRepository<Edition, Long> {
}
