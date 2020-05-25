package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Project;
import ru.itmo.sdb.models.ProjectParticipant;

@Repository
public interface ProjectParticipantRepository extends CrudRepository<ProjectParticipant, Long> {
}
