package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Subject;
import ru.itmo.sdb.models.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
}
