package ru.itmo.sdb.postgres.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.postgres.core.models.Student;

@Repository
public interface PostgresStudentRepository extends CrudRepository<Student, Long> {
}
