package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
}
