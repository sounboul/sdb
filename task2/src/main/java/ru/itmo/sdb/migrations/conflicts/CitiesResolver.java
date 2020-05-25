package ru.itmo.sdb.migrations.conflicts;

import ru.itmo.sdb.migrations.conflicts.meta.MigrationReport;
import ru.itmo.sdb.models.City;
import ru.itmo.sdb.models.Conference;
import ru.itmo.sdb.models.Dormitory;
import ru.itmo.sdb.models.Edition;
import ru.itmo.sdb.models.Person;
import ru.itmo.sdb.repositories.StoreRepositoryHelper;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CitiesResolver implements ConflictResolver {
    private StoreRepositoryHelper storeRepositoryHelper;

    public CitiesResolver(StoreRepositoryHelper storeRepositoryHelper) {
        this.storeRepositoryHelper = storeRepositoryHelper;
    }

    @Override
    public void resolveConflicts(List<MigrationReport> migrationReports) {
        Map<String, List<City>> conflictCities = StreamSupport
                .stream(storeRepositoryHelper.get(City.class).findAll().spliterator(), false)
                .collect(Collectors.groupingBy(City::getName));

        conflictCities.values()
                .forEach(cities -> {
                    City target = cities.remove(0);

                    if (cities.isEmpty()) {
                        return;
                    }

                    reduce(target, cities, Edition.class, Edition::getCity, Edition::setCity);
                    reduce(target, cities, Conference.class, Conference::getCity, Conference::setCity);
                    reduce(target, cities, Dormitory.class, Dormitory::getCity, Dormitory::setCity);
                    reduce(target, cities, Person.class, Person::getCity, Person::setCity);

                    storeRepositoryHelper.get(City.class)
                            .deleteAll(cities);
                });

    }

    private <T> void reduce(City target, List<City> cities, Class<T> entityClass, Function<T, City> extractor,
                            BiConsumer<T, City> updater) {

        cities.forEach(x -> {
            final List<T> entities = StreamSupport
                    .stream(storeRepositoryHelper.get(entityClass).findAll().spliterator(), false)
                    .filter(entity -> extractor.apply(entity) != null)
                    .filter(entity -> extractor.apply(entity)
                            .getId()
                            .equals(x.getId()))
                    .peek(entity -> updater.accept(entity, target))
                    .collect(Collectors.toList());

            storeRepositoryHelper.get(entityClass)
                    .saveAll(entities);
        });

    }
}
