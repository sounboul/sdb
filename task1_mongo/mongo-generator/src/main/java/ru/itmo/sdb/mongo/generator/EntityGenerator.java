package ru.itmo.sdb.mongo.generator;

import com.github.javafaker.Faker;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntityGenerator {
    public static <E> E generate(Class<E> eClass) {
        Faker faker = new Faker();

        Constructor[] ctors = eClass.getConstructors();
        for (Constructor ctor : ctors) {
            List<Object> args = new ArrayList<Object>();
            for (Class cls : ctor.getParameterTypes()) {
                if (cls.equals(String.class)) {
                    args.add(faker.pokemon().name());
                } else if (cls.equals(Integer.class)) {
                    args.add(new Integer(faker.number().numberBetween(0, 32000)));
                } else if (cls.equals(Date.class)) {
                    args.add(faker.date().birthday());
                } else if (cls.equals(Boolean.class)) {
                    args.add(new Boolean(faker.bool().bool()));
                } else {
                    args.add(null);
                }
            }

            try {
                return (E) ctor.newInstance(args.toArray());
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }
}
