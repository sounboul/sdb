package ru.itmo.sdb.mysql.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.mysql.core.models.PublicationEntity;

@Repository
public interface MySQLPublicationRepository extends CrudRepository<PublicationEntity, Long> {
}