package ru.itmo.sdb.oracle.generators;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.oracle.models.Specialty;
import ru.itmo.sdb.oracle.models.StudyGroup;

@Service
public class StudyGroupGeneration implements Generator<StudyGroup> {
    private Faker faker;

    @Autowired
    public StudyGroupGeneration(Faker faker) {
        this.faker = faker;
    }

    @Override
    public StudyGroup generate() {
        final StudyGroup studyGroup = new StudyGroup();

        studyGroup.setName(faker.regexify("[A-Z][0-9]{4}"));
        studyGroup.setCourse(faker.number().numberBetween(1, 5));

        final int startYear = faker.number().numberBetween(2000, 2021);
        studyGroup.setStartYear(startYear);
        studyGroup.setEndYear(startYear + 4);

        return studyGroup;
    }

    public StudyGroup generate(Specialty specialty) {
        final StudyGroup studyGroup = this.generate();

        studyGroup.setSpecialty(specialty);

        return studyGroup;
    }
}
