package ru.itmo.sdb.oracle.core.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.oracle.core.models.Department;

@Repository
public interface OracleDepartmentRepository extends CrudRepository<Department, Long> {
}
