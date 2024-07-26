package org.example.example2.config;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.descriptor.sql.internal.CapacityDependentDdlType;
import org.hibernate.type.descriptor.sql.spi.DdlTypeRegistry;

import lombok.extern.slf4j.Slf4j;

/**
 * Custom MySQL Dialect
 * - Define MEDIUMTEXT(16MB) for CLOB(2005) types
 * - JPA 를 통해 @Lob 어노테이션을 사용할 경우, 기본적으로 MySQL 의 TEXT 타입 or TINYTEXT 으로 매핑되는데, 이를 MEDIUMTEXT 로 변경
 */
@Slf4j
public class CustomMySQLDialect extends MySQLDialect {

	@Override
	protected void registerColumnTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
		super.registerColumnTypes(typeContributions, serviceRegistry);
		DdlTypeRegistry ddlTypeRegistry = typeContributions.getTypeConfiguration().getDdlTypeRegistry();

		ddlTypeRegistry.addDescriptor(
			CapacityDependentDdlType.builder(
					2005,
					CapacityDependentDdlType.LobKind.ALL_LOB,
					"mediumtext",
					"text",
					"varchar",
					this
				)
				.withTypeCapacity(16777215L, "mediumtext")
				.build()
		);
	}
}
