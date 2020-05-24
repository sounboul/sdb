package ru.itmo.sdb.oracle.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.oracle.models.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
