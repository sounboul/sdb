package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.ConferenceEntity;

@Repository
public interface ConferenceRepository extends CrudRepository<ConferenceEntity, Long> {
}
