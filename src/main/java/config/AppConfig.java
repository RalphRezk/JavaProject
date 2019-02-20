package config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import model.*;

import org.apache.commons.dbcp2.BasicDataSource;

import static org.hibernate.cfg.AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS;
import static org.hibernate.cfg.Environment.*;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = { 
	@ComponentScan("dao"),
    @ComponentScan("services")
})
public class AppConfig {

  @Autowired
  private Environment env;
  
  @Bean
  public DataSource getDataSource() {
     BasicDataSource dataSource = new BasicDataSource();
     dataSource.setDriverClassName(env.getProperty("sql.driver"));
     dataSource.setUrl(env.getProperty("sql.jdbcUrl"));
     dataSource.setUsername(env.getProperty("sql.username"));
     dataSource.setPassword(env.getProperty("sql.password"));
     return dataSource;
  }

  @Bean
  public LocalSessionFactoryBean getSessionFactory() {
    LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
    factoryBean.setDataSource(getDataSource());
    Properties props = new Properties();
    

    // Setting Hibernate properties
    props.put(DIALECT, env.getProperty("hibernate.dialect"));
    props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
    props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
    props.put(ENABLE_LAZY_LOAD_NO_TRANS, true);
    
    // Setting C3P0 properties
    props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
    props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
    props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
    props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
    props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));



    factoryBean.setHibernateProperties(props);
    factoryBean.setAnnotatedClasses(AbstractQuestion.class,MultipleChoiceQuestion.class, Option.class);
    return factoryBean;
  }

  @Bean
  public HibernateTransactionManager getTransactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(getSessionFactory().getObject());
    return transactionManager;
  }
  
}
