package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.CityEntity;

@Repository
public interface CityRepository extends CrudRepository<CityEntity, Long> {
}
