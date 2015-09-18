package com.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by aakture on 9/17/15.
 */
public enum ConnectionPool {

    INSTANCE;

    private final Logger log = LoggerFactory.getLogger(ConnectionPool.class);
    private HikariDataSource ds;

    private ConnectionPool() {
        log.info("Construction of ConnectionPool");
        String jdbcUrl = "jdbc:mysql://localhost:3306/test?useServerPrepStmts=true&useAffectedRows=true";
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(2);
        config.setConnectionTimeout(5500);
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.addDataSourceProperty("url", jdbcUrl);
        config.addDataSourceProperty("user", "root");
        config.addDataSourceProperty("password", "");

        config.addDataSourceProperty("cachePrepStmts", true);
        config.addDataSourceProperty("prepStmtCacheSize", 250);
        config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        config.addDataSourceProperty("autoReconnect", true);
        config.setConnectionTestQuery("SELECT 1");
        log.info("initializing database connection pool to: {}. If this takes a long time, check that the URL, user, and password are all correct.", jdbcUrl);
        ds = new HikariDataSource(config);
        log.info("initializing database connection pool complete");

    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * Only call this when the app shuts down to close pool of if you need to
     */
    public void close() {
        log.info("shutting down pool");
        try {
            if (ds != null) {
                ds.close();
            }
        }catch(Exception ex) {
            log.error("error closing pool", ex);
        }
    }

}
