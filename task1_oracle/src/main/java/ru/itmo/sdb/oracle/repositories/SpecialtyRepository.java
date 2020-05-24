package ru.itmo.sdb.oracle.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.oracle.models.Specialty;
import ru.itmo.sdb.oracle.models.Student;

@Repository
public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
