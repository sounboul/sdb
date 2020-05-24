package ru.itmo.sdb.mysql.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.mysql.core.models.ProjectParticipantEntity;

@Repository
public interface ProjectParticipantRepository extends CrudRepository<ProjectParticipantEntity, Long> {
}
