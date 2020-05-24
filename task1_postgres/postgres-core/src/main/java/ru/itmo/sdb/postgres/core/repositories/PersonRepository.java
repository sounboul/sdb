package ru.itmo.sdb.postgres.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.postgres.core.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
