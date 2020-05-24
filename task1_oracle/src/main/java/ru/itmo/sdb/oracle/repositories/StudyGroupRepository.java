package ru.itmo.sdb.oracle.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.oracle.models.StudyGroup;

@Repository
public interface StudyGroupRepository extends CrudRepository<StudyGroup, Long> {
}
