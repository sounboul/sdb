package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Room;
import ru.itmo.sdb.models.University;

@Repository
public interface UniversityRepository extends CrudRepository<University, Long> {
}
