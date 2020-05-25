package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Direction;
import ru.itmo.sdb.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
