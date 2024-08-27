
package org.example.example2.config;

import static org.example.example2.config.AbstractDataSourceConfig.*;

import java.util.Objects;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import lombok.Generated;

// @Generated 를 추가하면 jacoco 에서 무시하도록 설정할 수 있습니다.
@Generated
@Configuration
@ConditionalOnProperty(name = "spring.datasource.db-type", havingValue = ORACLE_PERSISTENCE_UNIT)
public class OracleDataSourceConfig extends AbstractDataSourceConfig {

	@Override
	@Bean(name = DATA_SOURCE_BEAN_NAME)
	@ConfigurationProperties(prefix = "spring.datasource.oracle")
	public HikariDataSource dataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Override
	@Bean(name = ENTITY_MANAGER_FACTORY_BEAN_NAME)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
		EntityManagerFactoryBuilder builder, HikariDataSource dataSource
	) {
		dataSource.setPoolName("Oracle-Hikari-Pool");
		return builder.dataSource(dataSource)
			.packages(ENTITY_PACKAGE)
			.persistenceUnit(ORACLE_PERSISTENCE_UNIT)
			.build();
	}

	@Override
	@Bean(name = TRANSACTION_MANAGER_BEAN_NAME)
	public PlatformTransactionManager transactionManager(
		LocalContainerEntityManagerFactoryBean entityManagerFactory
	) {
		return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
	}
}
