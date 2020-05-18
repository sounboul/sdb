package ru.itmo.sdb.xmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itmo.sdb.xmongo.entities.Tenant;

public interface Tenants extends MongoRepository<Tenant, Integer> {}
