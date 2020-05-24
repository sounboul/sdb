package ru.itmo.sdb.mongo.core.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itmo.sdb.mongo.core.entities.Tenant;

public interface Tenants extends MongoRepository<Tenant, Integer> {}
