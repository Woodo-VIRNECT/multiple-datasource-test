
package org.example.example2.config;

import static org.example.example2.config.AbstractDataSourceConfig.*;

import java.util.Objects;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConditionalOnProperty(name = "spring.datasource.db-type", havingValue = MYSQL_PERSISTENCE_UNIT, matchIfMissing = true)
public class MysqlDataSourceConfig extends AbstractDataSourceConfig {

	@Override
	@Primary
	@Bean(name = DATA_SOURCE_BEAN_NAME)
	@ConfigurationProperties(prefix = "spring.datasource.mysql")
	public HikariDataSource dataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Override
	@Primary
	@Bean(name = ENTITY_MANAGER_FACTORY_BEAN_NAME)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
		EntityManagerFactoryBuilder builder, HikariDataSource dataSource
	) {
		dataSource.setPoolName("Mysql-Hikari-Pool");
		return builder.dataSource(dataSource)
			.packages(ENTITY_PACKAGE)
			.persistenceUnit(MYSQL_PERSISTENCE_UNIT)
			.build();
	}

	@Override
	@Primary
	@Bean(name = TRANSACTION_MANAGER_BEAN_NAME)
	public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
	}
}
