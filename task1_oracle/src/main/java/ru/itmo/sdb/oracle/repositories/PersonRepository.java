package ru.itmo.sdb.oracle.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.oracle.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
