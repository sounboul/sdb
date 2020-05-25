package ru.itmo.sdb.mysql.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.mysql.core.models.PublicationAuthorsEntity;

@Repository
public interface MySQLPublicationAuthorsRepository extends CrudRepository<PublicationAuthorsEntity, Long> {
    Iterable<PublicationAuthorsEntity> findByPublicationId(long publicationId);
}
