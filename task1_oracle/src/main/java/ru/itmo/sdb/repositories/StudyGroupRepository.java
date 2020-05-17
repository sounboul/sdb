package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.StudyGroup;

@Repository
public interface StudyGroupRepository extends CrudRepository<StudyGroup, Long> {
}
