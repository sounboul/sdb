package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Publication;
import ru.itmo.sdb.models.Specialty;

@Repository
public interface PublicationRepository extends CrudRepository<Publication, Long> {
}
