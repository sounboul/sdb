package ru.itmo.sdb.oracle.generator;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("ru.itmo.sdb.oracle")
@EnableJpaRepositories(basePackages = "ru.itmo.sdb.oracle.core")
@EntityScan("ru.itmo.sdb.oracle.core.models")
public class CoreConfiguration {
}
