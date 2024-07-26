package org.example.example2.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import lombok.Getter;

@Getter
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = AbstractDataSourceConfig.REPOSITORY_PACKAGE)
public abstract class AbstractDataSourceConfig {

	public static final String DATA_SOURCE_BEAN_NAME = "dataSource";
	public static final String ENTITY_MANAGER_FACTORY_BEAN_NAME = "entityManagerFactory";
	public static final String TRANSACTION_MANAGER_BEAN_NAME = "transactionManager";
	public static final String REPOSITORY_PACKAGE = "org.example.example2.repository";
	public static final String ENTITY_PACKAGE = "org.example.example2.entity";
	public static final String MYSQL_PERSISTENCE_UNIT = "mysql";
	public static final String ORACLE_PERSISTENCE_UNIT = "oracle";

	public abstract HikariDataSource dataSource();

	public abstract LocalContainerEntityManagerFactoryBean entityManagerFactory(
		EntityManagerFactoryBuilder builder, @Qualifier(DATA_SOURCE_BEAN_NAME) HikariDataSource dataSource
	);

	public abstract PlatformTransactionManager transactionManager(
		@Qualifier(ENTITY_MANAGER_FACTORY_BEAN_NAME) LocalContainerEntityManagerFactoryBean entityManagerFactory
	);
}