package mydomain.repository;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.datanucleus.api.jpa.PersistenceProviderImpl;
import org.datanucleus.store.rdbms.datasource.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "mydomain.repository")
public class UnitTestConfiguration {

  @Bean
  DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setUrl("jdbc:h2:mem:nucleus");
    dataSource.setDriverClassName("org.h2.Driver");
    dataSource.setUsername("sa");
    return dataSource;
  }

  @Bean
  LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean entityManagerFactory =
        new LocalContainerEntityManagerFactoryBean();
    entityManagerFactory.setDataSource(dataSource);
    entityManagerFactory.setPackagesToScan("mydomain.repository");
    entityManagerFactory.setPersistenceProviderClass(PersistenceProviderImpl.class);
    entityManagerFactory.setPersistenceUnitName("MyTest");
    // entityManagerFactory.setLoadTimeWeaver(new DefaultContextLoadTimeWeaver());
    return entityManagerFactory;
  }

  @Bean
  @DependsOn("entityManagerFactory")
  PlatformTransactionManager transactionManager(DataSource dataSource, EntityManagerFactory emf) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setDataSource(dataSource);
    transactionManager.setEntityManagerFactory(emf);
    return transactionManager;
  }
}
