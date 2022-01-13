package com.kpi.payments.model.dao.impl;

import com.kpi.payments.entity.Account;
import com.kpi.payments.exception.DaoException;
import com.kpi.payments.model.dao.AbstractDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDao extends AbstractDAO<Long, Account> {

    private static final String ACCOUNTS_BY_USER_ID =
            "SELECT * FROM accounts a LEFT JOIN" +
                    "cards c ON a.account_number = c.account_number" +
                    "WHERE c.user_id = ?" +
                    "ORDER BY ? ?";

    public static final String UPDATE_LOCKED = "UPDATE accounts SET locked = ? WHERE account_number = ?";

    @Override
    public List<Account> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Account> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Account entity) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Account entity) throws DaoException {
        return false;
    }

    @Override
    public Account update(Account entity) throws DaoException {
        return null;
    }

    public List<Account> findAllByUserId(String sortBy, String sortDirection, long userId) throws DaoException {
        List<Account> accounts = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(ACCOUNTS_BY_USER_ID)) {
            statement.setLong(1, userId);
            statement.setString(2, sortBy);
            statement.setString(3, sortDirection);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Account account = getAccountFromResultSet(resultSet);
                accounts.add(account);
            }
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage());
        }

        return accounts;
    }

    private Account getAccountFromResultSet(ResultSet resultSet) throws SQLException {
        return Account.builder()
                .accountNumber(resultSet.getLong("account_number"))
                .moneyAmount(resultSet.getBigDecimal("money_amount"))
                .locked(resultSet.getBoolean("locked"))
                .accountName(resultSet.getString("account_name"))
                .build();
    }

    public void lockAccount(long accountId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_LOCKED)) {
            statement.setBoolean(1, true);
            statement.setLong(2, accountId);

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage());
        }
    }

    public void unlockAccount(long accountId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_LOCKED)) {
            statement.setBoolean(1, false);
            statement.setLong(2, accountId);

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage());
        }
    }
}
