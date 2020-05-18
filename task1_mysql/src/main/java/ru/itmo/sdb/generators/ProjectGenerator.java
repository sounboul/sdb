package ru.itmo.sdb.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.models.ProjectEntity;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

@Service
public class ProjectGenerator implements Generator<ProjectEntity> {
    private Faker faker;

    @Autowired
    public ProjectGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public ProjectEntity generate() {
        final ProjectEntity project = new ProjectEntity();

        project.setName(faker.gameOfThrones().dragon());
        project.setStartDate(new java.sql.Date(faker.date().past(360 * 5, TimeUnit.DAYS).getTime()));
        project.setFinishDate(new java.sql.Date(faker.date().between(
                project.getStartDate(), faker.date().future(360, TimeUnit.DAYS)
        ).getTime()));

        return project;
    }
}
