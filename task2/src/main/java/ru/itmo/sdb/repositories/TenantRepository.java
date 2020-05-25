package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Person;
import ru.itmo.sdb.models.Tenant;

@Repository
public interface TenantRepository extends CrudRepository<Tenant, Long> {
}
