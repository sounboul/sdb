package ru.itmo.sdb.mongo.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.itmo.sdb.mongo.core.entities.Dormitory;
import ru.itmo.sdb.mongo.core.entities.Penalty;
import ru.itmo.sdb.mongo.core.entities.Room;
import ru.itmo.sdb.mongo.core.entities.Tenant;
import ru.itmo.sdb.mongo.core.entities.Visit;
import ru.itmo.sdb.mongo.core.repositories.Dorms;
import ru.itmo.sdb.mongo.core.repositories.Penalties;
import ru.itmo.sdb.mongo.core.repositories.Rooms;
import ru.itmo.sdb.mongo.core.repositories.Tenants;
import ru.itmo.sdb.mongo.core.repositories.Visits;
import ru.itmo.sdb.mongo.generator.generators.DormitoryGenerator;
import ru.itmo.sdb.mongo.generator.generators.PenaltyGenerator;
import ru.itmo.sdb.mongo.generator.generators.RoomGenerator;
import ru.itmo.sdb.mongo.generator.generators.TenantGenerator;
import ru.itmo.sdb.mongo.generator.generators.VisitGenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("ALL")
@SpringBootApplication
@EnableMongoRepositories(basePackages = "ru.itmo.sdb.mongo.core")
public class MongoDataGenerator implements CommandLineRunner {

    @Autowired
    private Dorms dormsRepository;

    @Autowired
    private Penalties penaltiesRepository;

    @Autowired
    private Rooms roomsRepository;

    @Autowired
    private Tenants tenantsRepository;

    @Autowired
    private Visits visitsRepository;

    @Autowired
    private DormitoryGenerator dormitoryGenerator;

    @Autowired
    private PenaltyGenerator penaltyGenerator;

    @Autowired
    private RoomGenerator roomGenerator;

    @Autowired
    private TenantGenerator tenantGenerator;

    @Autowired
    private VisitGenerator visitGenerator;

    public static void main(String[] args) {
        SpringApplication.run(MongoDataGenerator.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final List<Dormitory> dormitories = IntStream.rangeClosed(1, 3).boxed()
                .map(x -> dormitoryGenerator.generate()).collect(Collectors.toList());

        final List<Room> rooms = dormitories.stream()
                .flatMap(x -> IntStream.rangeClosed(1, 5).boxed().map(i -> roomGenerator.generate(x)))
                .collect(Collectors.toList());

        final List<Tenant> tenants = rooms.stream()
                .flatMap(x -> IntStream.rangeClosed(1, x.capacity).boxed().map(i -> tenantGenerator.generate(x)))
                .collect(Collectors.toList());

        final List<Visit> visits = dormitories.stream()
                .flatMap(x -> IntStream.rangeClosed(1, 10).boxed().map(i -> visitGenerator.generate(x)))
                .collect(Collectors.toList());

        final List<Penalty> penalties = dormitories.stream()
                .flatMap(x -> IntStream.rangeClosed(1, 5).boxed().map(i -> {
                    final Room room = rooms.stream().filter(r -> r.dormitory_id.equals(x.id)).findAny().get();
                    final Tenant tenant = tenants.stream().filter(t -> t.room_id.equals(room.id)).findAny().get();
                    return penaltyGenerator.generate(x, tenant);
                }))
                .collect(Collectors.toList());

        dormsRepository.saveAll(dormitories);
        roomsRepository.saveAll(rooms);
        tenantsRepository.saveAll(tenants);
        visitsRepository.saveAll(visits);
        penaltiesRepository.saveAll(penalties);
    }
}
