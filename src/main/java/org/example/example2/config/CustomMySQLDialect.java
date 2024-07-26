package org.example.example2.config;

import java.sql.Types;

import org.hibernate.dialect.MySQLDialect;

public class MysqlDBCustomDialect extends MySQLDialect {

	public MysqlDBCustomDialect() {
		super();
		registerColumnType(Types.CLOB, "longtext");
	}
}
