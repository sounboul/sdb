package ru.itmo.sdb.mysql.generator.generators;

import com.github.javafaker.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RandomHelper {
    @Autowired
    private RandomService randomService;

    public <T> T randomFrom(List<T> list) {
        final int index = randomService.nextInt(0, list.size() - 1);

        return list.get(index);
    }
}
