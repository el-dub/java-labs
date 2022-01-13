package com.kpi.payments.model.dao.connection;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionPoolHolder {

    private static volatile DataSource dataSource;

    private static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                BasicDataSource ds = new BasicDataSource();
//                ds.setUrl();
            }
        }
        return null;
    }
}
