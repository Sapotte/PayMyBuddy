package com.paymybuddy.configuration;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;

public class DataSourceConfig {
    public static DataSource createDataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        ds.setUser("paymybuddy");
        ds.setPassword("paymybuddy");
        return ds;
    }
}
