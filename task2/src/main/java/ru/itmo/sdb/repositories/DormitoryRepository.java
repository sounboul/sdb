package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Dormitory;

@Repository
public interface DormitoryRepository extends CrudRepository<Dormitory, Long> {
}
