package ru.itmo.sdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itmo.sdb.migrations.MongoMigration;
import ru.itmo.sdb.migrations.MySQLMigration;
import ru.itmo.sdb.migrations.OracleMigration;
import ru.itmo.sdb.migrations.PostgresMigration;
import ru.itmo.sdb.migrations.TechGenerator;
import ru.itmo.sdb.migrations.conflicts.CitiesResolver;

@SpringBootApplication
public class Migration implements CommandLineRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Migration.class, args);
    }

    private MongoMigration mongoMigration;
    private OracleMigration oracleMigration;
    private PostgresMigration postgresMigration;
    private MySQLMigration mySQLMigration;
    private CitiesResolver citiesResolver;
    private TechGenerator techGenerator;

    @Autowired
    public Migration(MongoMigration mongoMigration, OracleMigration oracleMigration,
                     PostgresMigration postgresMigration, MySQLMigration mySQLMigration,
                     CitiesResolver citiesResolver, TechGenerator techGenerator) {
        this.mongoMigration = mongoMigration;
        this.oracleMigration = oracleMigration;
        this.postgresMigration = postgresMigration;
        this.mySQLMigration = mySQLMigration;
        this.citiesResolver = citiesResolver;
        this.techGenerator = techGenerator;
    }

    @Override
    public void run(String... args) throws Exception {
        mongoMigration.migrate();
        oracleMigration.migrate();
        postgresMigration.migrate();
        mySQLMigration.migrate();
        citiesResolver.resolveConflicts(null);
        techGenerator.generate();
    }
}
