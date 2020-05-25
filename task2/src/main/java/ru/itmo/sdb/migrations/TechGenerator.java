package ru.itmo.sdb.migrations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.models.technical.XTime;
import ru.itmo.sdb.repositories.XTimeRepository;

@Service
public class TechGenerator {
    @Autowired
    private XTimeRepository xTimeRepository;

    public void generate() {
        for (int year = 1920; year < 2030; year++) {
            for (int term = 1; term < 2; term++) {
                XTime xTime = new XTime();

                xTime.setYear(year);
                xTime.setTerm(term);

                xTimeRepository.save(xTime);
            }
        }

    }
}
