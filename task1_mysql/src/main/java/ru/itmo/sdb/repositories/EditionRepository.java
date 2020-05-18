package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.EditionEntity;

@Repository
public interface EditionRepository extends CrudRepository<EditionEntity, Long> {
}
