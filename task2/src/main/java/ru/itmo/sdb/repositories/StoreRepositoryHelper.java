package ru.itmo.sdb.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.itmo.sdb.migrations.conflicts.MigrationMeta;
import ru.itmo.sdb.models.City;
import ru.itmo.sdb.models.Dormitory;
import ru.itmo.sdb.models.Identifiable;
import ru.itmo.sdb.models.Person;
import ru.itmo.sdb.models.Room;
import ru.itmo.sdb.models.Tenant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StoreRepositoryHelper {
    private CityRepository cityRepository;
    private DormitoryRepository dormitoryRepository;
    private PersonRepository personRepository;
    private RoomRepository roomRepository;
    private TenantRepository tenantRepository;

    private Map<Class, CrudRepository> mapping = new HashMap<>();

    @Autowired
    public StoreRepositoryHelper(CityRepository cityRepository, DormitoryRepository dormitoryRepository, PersonRepository personRepository, RoomRepository roomRepository, TenantRepository tenantRepository) {
        this.cityRepository = cityRepository;
        this.dormitoryRepository = dormitoryRepository;
        this.personRepository = personRepository;
        this.roomRepository = roomRepository;
        this.tenantRepository = tenantRepository;

        mapping.put(City.class, cityRepository);
        mapping.put(Dormitory.class, dormitoryRepository);
        mapping.put(Person.class, personRepository);
        mapping.put(Room.class, roomRepository);
        mapping.put(Tenant.class, tenantRepository);
    }

    public <T> CrudRepository<T, Long> get(Class<T> clazz) {
        return mapping.get(clazz);
    }

    public <T extends Identifiable> MigrationMeta<T> saveWithReport(Iterable<T> data, Class<T> dataClass) {
        final Iterable<T> savedData = this.get(dataClass).saveAll(data);
        final List<Long> ids = StreamSupport.stream(savedData.spliterator(), false)
                .map(Identifiable::getId)
                .collect(Collectors.toList());

        return new MigrationMeta<T>(dataClass, ids);
    }


}
