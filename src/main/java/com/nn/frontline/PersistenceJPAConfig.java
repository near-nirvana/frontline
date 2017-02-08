package com.nn.frontline;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by santiago.ginzburg on 2/3/16.
 */
@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig {

    public static final Logger LOGGER = LoggerFactory.getLogger(PersistenceJPAConfig.class);

    /**
     * c3p0 max size pool.
     * */
    @Value("${c3p0.max_size}")
    private int maxSize;

    /**
     * c3p0 min size pool.
     * */
    @Value("${c3p0.min_size}")
    private int minSize;

    /**
     * c3p0 acquire increment pool.
     * */
    @Value("${c3p0.acquire_increment}")
    private int acquireIncrement;

    /**
     * c3p0 idle test period.
     * */
    @Value("${c3p0.idle_test_period}")
    private int idleTestPeriod;

    /**
     * c3p0 max statements exec.
     * */
    @Value("${c3p0.max_statements}")
    private int maxStatements;

    /**
     * c3p0 max idle time.
     * */
    @Value("${c3p0.max_idle_time}")
    private int maxIdleTime;

    /**
     * c3p0 db url.
     * */
    @Value("${c3p0.url}")
    private String url;

    /**
     * c3p0 db username.
     * */
    @Value("${c3p0.username}")
    private String username;

    /**
     * c3p0 db password.
     * */
    @Value("${c3p0.password}")
    private String password;

    /**
     * c3p0 driver classname.
     * */
    @Value("${c3p0.driverClassName}")
    private String driverClassName;

    /**
     * c3p0 test query.
     * */
    @Value("${c3p0.preferredTestQuery}")
    private String preferredTestQuery;

    //@Value("${spring.jpa.show-sql}")
    //private String showSql;

    //@Value("${spring.jpa.hibernate.ddl-auto}")
    //private String ddlAuto;

    //@Value("${spring.jpa.hibernate.naming-strategy}")
    //private String nameingStrategy;

    //@Value("${spring.jpa.properties.hibernate.dialect}")
    //private String dialect;



    @Bean
    public DataSource dataSource()  throws PropertyVetoException {

        final ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setMaxPoolSize(maxSize);
        dataSource.setMinPoolSize(minSize);
        dataSource.setAcquireIncrement(acquireIncrement);
        dataSource.setIdleConnectionTestPeriod(idleTestPeriod);
        dataSource.setMaxStatements(maxStatements);
        dataSource.setMaxIdleTime(maxIdleTime);
        dataSource.setJdbcUrl(url);
        dataSource.setPassword(password);
        dataSource.setUser(username);
        dataSource.setDriverClass(driverClassName);
        dataSource.setPreferredTestQuery(preferredTestQuery);
        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @PostConstruct
    public void startDBManager() {

        //hsqldb
        //DatabaseManagerSwing.main(new String[]{"--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", ""});

    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.nn.frontline.model" });

        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf){
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }
}
