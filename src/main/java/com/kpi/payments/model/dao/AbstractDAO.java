package com.kpi.payments.model.dao;

import com.kpi.payments.entity.Entity;
import com.kpi.payments.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractDAO<K, T extends Entity> {
    protected Connection connection;

    public abstract List<T> findAll() throws DaoException;

    public abstract Optional<T> findById(K id) throws DaoException;

    public abstract boolean delete(K id) throws DaoException;

    public abstract boolean delete(T entity) throws DaoException;

    public abstract boolean create(T entity) throws DaoException;

    public abstract T update(T entity) throws DaoException;

    protected void closeStatement(Statement statement) {
        try {
            if (Objects.nonNull(statement)) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
