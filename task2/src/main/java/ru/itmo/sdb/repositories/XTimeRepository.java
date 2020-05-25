package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.sdb.models.technical.XTime;

public interface XTimeRepository extends CrudRepository<XTime, Long> {
}
