package ru.itmo.sdb;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@ComponentScan("ru.itmo.sdb")
@EntityScan("ru.itmo.sdb.models")
public class CoreConfiguration {
}
