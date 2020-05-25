package ru.itmo.sdb.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "ru.itmo.sdb.models",
        entityManagerFactoryRef = "finalDatabaseEntityManager",
        transactionManagerRef = "finalDatabaseTransactionManager"
)
public class FinalDatabaseConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean finalDatabaseEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userDataSource());
        em.setPackagesToScan("ru.itmo.sdb.finalDatabase.core");

        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.finalDatabase.datasource")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager finalDatabaseTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(finalDatabaseEntityManager().getObject());
        return transactionManager;
    }
}
