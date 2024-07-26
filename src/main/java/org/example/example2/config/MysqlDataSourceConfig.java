package org.example.multipledatasource.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	entityManagerFactoryRef = "mysqlEntityManagerFactory",
	transactionManagerRef = "mysqlTransactionManager",
	basePackages = { "org.example.multipledatasource.repository.mysql" }
)
public class MysqlDataSourceConfig {

	@Bean(name = "mysqlDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.mysql")
	public DataSource dataSource() {
		System.out.println(">>> mysqlDataSource");
		return DataSourceBuilder.create().build();
		// return DataSourceBuilder.create().type(DriverSpy.class).build();
	}

	@Bean(name = "mysqlEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
		@Qualifier("mysqlDataSource") DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan("org.example.multipledatasource.entity.mysql");
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setPersistenceUnitName("mysqlPU");

		// em.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

		System.out.println(">>> mysqlEntityManagerFactory");
		return em;
	}

	@Bean(name = "mysqlTransactionManager")
	public PlatformTransactionManager transactionManager(
		@Qualifier("mysqlEntityManagerFactory") LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory) {
		System.out.println(">>> mysqlTransactionManager");
		return new JpaTransactionManager(mysqlEntityManagerFactory.getObject());
	}
}