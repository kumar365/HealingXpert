package com.healthcare.healingxpert.config;

import org.hibernate.dialect.PostgreSQL95Dialect;
import org.springframework.context.annotation.Configuration;

public class PostgreSQLCustomDialect extends PostgreSQL95Dialect {
    public PostgreSQLCustomDialect() {
        super();
        registerColumnType(java.sql.Types.BLOB, "bytea");
        registerColumnType(java.sql.Types.BINARY, "bytea");
        registerColumnType(java.sql.Types.VARBINARY, "bytea");
        registerColumnType(java.sql.Types.LONGVARBINARY, "bytea");
    }
}
