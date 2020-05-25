package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Conference;
import ru.itmo.sdb.models.Edition;

@Repository
public interface ConferenceRepository extends CrudRepository<Conference, Long> {
}
