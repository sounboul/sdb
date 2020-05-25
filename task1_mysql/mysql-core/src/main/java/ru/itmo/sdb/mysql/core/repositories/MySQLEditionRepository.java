package ru.itmo.sdb.mysql.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.mysql.core.models.EditionEntity;

@Repository
public interface MySQLEditionRepository extends CrudRepository<EditionEntity, Long> {
}
