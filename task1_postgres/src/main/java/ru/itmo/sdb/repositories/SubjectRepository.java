package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
}
