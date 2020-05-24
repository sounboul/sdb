package ru.itmo.sdb.postgres.generator;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.itmo.sdb.postgres.core")
@ComponentScan("ru.itmo.sdb.postgres")
@EntityScan("ru.itmo.sdb.postgres.core.models")
public class CoreConfiguration {
}
