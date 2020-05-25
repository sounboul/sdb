package ru.itmo.sdb.migrations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.migrations.conflicts.meta.MigrationMeta;
import ru.itmo.sdb.migrations.conflicts.meta.MigrationReport;
import ru.itmo.sdb.migrations.conflicts.meta.Source;
import ru.itmo.sdb.models.Admonition;
import ru.itmo.sdb.models.City;
import ru.itmo.sdb.models.Dormitory;
import ru.itmo.sdb.models.Identifiable;
import ru.itmo.sdb.models.Person;
import ru.itmo.sdb.models.Room;
import ru.itmo.sdb.models.Tenant;
import ru.itmo.sdb.models.Visit;
import ru.itmo.sdb.mongo.core.repositories.Dorms;
import ru.itmo.sdb.mongo.core.repositories.Penalties;
import ru.itmo.sdb.mongo.core.repositories.Rooms;
import ru.itmo.sdb.mongo.core.repositories.Tenants;
import ru.itmo.sdb.mongo.core.repositories.Visits;
import ru.itmo.sdb.repositories.StoreRepositoryHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MongoMigration {
    @Autowired
    private Dorms mongoDormitoryRepository;

    @Autowired
    private Penalties mongoPenaltiesRepository;

    @Autowired
    private Rooms mongoRoomRepository;

    @Autowired
    private Tenants mongoTenantsRepository;

    @Autowired
    private Visits mongoVisitsRepository;

    @Autowired
    private StoreRepositoryHelper repositoryHelper;

    public MigrationReport migrate() {
        final Map<String, City> cities = new HashMap<>();
        final Map<Long, Dormitory> dormitories = new HashMap<>();
        final Map<Long, Room> rooms = new HashMap<>();
        final List<Person> people = new ArrayList<>();
        final Map<Long, Tenant> tenants = new HashMap<>();

        mongoDormitoryRepository.findAll().forEach(x -> {
            final Dormitory dormitory = new Dormitory();

            dormitory.setName(x.name);
            dormitory.setAddress(x.address);

            City city = cities.get(x.city);

            if (city == null) {
                city = new City();
                city.setName(x.city);
                cities.put(x.city, city);
            }

            dormitory.setCity(city);

            dormitories.put(x.id, dormitory);
        });

        mongoRoomRepository.findAll().forEach(x -> {
            Room room = new Room();
            room.setCapacity(x.capacity);
            room.setBugs(x.bugs);
            room.setDisinfectionDate(new Date(x.disinfection_date.getTime()));
            room.setDormitory(dormitories.get(x.id));
            rooms.put(x.id, room);
        });

        mongoTenantsRepository.findAll().forEach(x -> {
            final Person person = new Person();

            person.setFirstName(x.firstName);
            person.setLastName(x.lastName);
            person.setPatronymic(x.patronymic);

            final Tenant tenant = new Tenant();
            tenant.setPaymentAmount(x.payment_amount);
            tenant.setPerson(person);
            tenant.setPaymentType(x.payment_type);
            tenant.setRoom(rooms.get(x.room_id));

            people.add(person);
            tenants.put(x.id, tenant);
        });

        final List<Visit> visits = new ArrayList<>();
        mongoVisitsRepository.findAll().forEach(x -> {
            final Person person = new Person();

            person.setFirstName(x.visitorFirstName);
            person.setLastName(x.visitorLastName);
            person.setPatronymic(x.visitorPatronymic);

            Visit visit = new Visit();
            visit.setDormitory(dormitories.get(x.dormitory_id));
            visit.setPerson(person);
            visit.setEnterDate(new Date(x.enter_date.getTime()));
            visit.setExitDate(new Date(x.exit_date.getTime()));

            people.add(person);
            visits.add(visit);
        });

        List<Admonition> admonitions = new ArrayList<>();
        mongoPenaltiesRepository.findAll().forEach(x -> {
            final Admonition admonition = new Admonition();

            admonition.setDormitory(dormitories.get(x.dormitory_id));
            admonition.setTenant(tenants.get(x.tenant_id));
            admonition.setReason(x.reason);
            admonition.setReportDate(new Date(x.report_date.getTime()));
            admonitions.add(admonition);
        });

        return new MigrationReport(Source.MONGO, Arrays.asList(
                save(cities.values(), City.class),
                save(dormitories.values(), Dormitory.class),
                save(rooms.values(), Room.class),
                save(people, Person.class),
                save(tenants.values(), Tenant.class),
                save(visits, Visit.class),
                save(admonitions, Admonition.class)
        ));
    }

    private <T extends Identifiable> MigrationMeta<T> save(Iterable<T> data, Class<T> dataClass) {
        return repositoryHelper.saveWithReport(data, dataClass);
    }
}
