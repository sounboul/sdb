package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Specialty;
import ru.itmo.sdb.models.Teacher;

@Repository
public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
