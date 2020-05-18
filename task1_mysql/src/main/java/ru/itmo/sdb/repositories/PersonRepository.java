package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.PersonEntity;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {}
