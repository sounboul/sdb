package ru.itmo.sdb.mysql.generator.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.mysql.core.models.ProjectParticipantEntity;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class ProjectParticipantGenerator implements Generator<ProjectParticipantEntity> {
    private final List<String> roles = Arrays.asList("доцент", "профессор");
    private Faker faker;

    @Autowired
    public ProjectParticipantGenerator(Faker faker) {
        this.faker = faker;
    }

    @Override
    public ProjectParticipantEntity generate() {
        final ProjectParticipantEntity projectParticipant = new ProjectParticipantEntity();

        projectParticipant.setStartDate(
                new Date(faker.date().past(360 * 5, TimeUnit.DAYS).getTime()
                ));
        projectParticipant.setFinishDate(new Date(faker.date().between(
                projectParticipant.getStartDate(), faker.date().future(360, TimeUnit.DAYS)
        ).getTime()));
        projectParticipant.setPersonId(faker.number().randomDigit() + 1);
        projectParticipant.setRole(roles.get(new Random().nextInt(roles.size())));

        return projectParticipant;
    }
}
