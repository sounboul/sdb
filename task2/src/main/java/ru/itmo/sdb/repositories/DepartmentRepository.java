package ru.itmo.sdb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.sdb.models.Admonition;
import ru.itmo.sdb.models.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
