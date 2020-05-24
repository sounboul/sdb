package ru.itmo.sdb.oracle.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.oracle.models.Department;

@Service
public class DepartmentGenerator implements Generator<Department> {
    private Faker faker;

    @Autowired
    public DepartmentGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public Department generate() {
        final Department department = new Department();

        department.setName(faker.educator().campus());
        department.setType(faker.pokemon().name());

        return department;
    }
}
