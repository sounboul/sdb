package ru.itmo.sdb.mysql.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.mysql.core.models.PersonEntity;

@Repository
public interface MySQLPersonRepository extends CrudRepository<PersonEntity, Long> {}
