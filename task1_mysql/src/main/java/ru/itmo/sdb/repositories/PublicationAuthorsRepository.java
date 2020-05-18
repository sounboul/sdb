package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.PublicationAuthorsEntity;

@Repository
public interface PublicationAuthorsRepository extends CrudRepository<PublicationAuthorsEntity, Long> {
}
