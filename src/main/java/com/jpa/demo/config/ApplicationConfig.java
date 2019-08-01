package com.jpa.demo.config;

import com.jpa.demo.dao.AddressRepository;
import com.jpa.demo.dao.CustomerRepository;
import com.jpa.demo.dao.impl.JpaAddressRepository;
import com.jpa.demo.dao.impl.JpaCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class ApplicationConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public DataSource getDataSrouce() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl("jdbc:mysql://localhost/jpa");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        // adapter.setGenerateDdl(true);
        // adapter.setDatabase(Database.MYSQL);

        Properties props = new Properties();

        props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        //props.setProperty("hibernate.hbm2ddl.auto", "update");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        //props.setProperty("spring.jpa.properties.hibernate.jdbc.time_zone","UTC");

        LocalContainerEntityManagerFactoryBean emfb =
                new LocalContainerEntityManagerFactoryBean();

        emfb.setDataSource(dataSource);
        emfb.setPackagesToScan("com.jpa.demo.dto");
        emfb.setJpaProperties(props);
        emfb.setJpaVendorAdapter(adapter);

        return emfb;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public CustomerRepository jpaCustomerRepository() {
        return new JpaCustomerRepository();
    }

    @Bean
    public AddressRepository jpaAddressRepository() {
        return new JpaAddressRepository();
    }
}
