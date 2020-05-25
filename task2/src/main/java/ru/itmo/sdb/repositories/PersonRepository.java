package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.City;
import ru.itmo.sdb.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
