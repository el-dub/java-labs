package com.kpi.payments.model.dao.impl;

import com.kpi.payments.entity.Payment;
import com.kpi.payments.exception.DaoException;
import com.kpi.payments.model.dao.AbstractDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDao extends AbstractDAO<Long, Payment> {

    private static final String CREATE_PAYMENT =
            "INSERT INTO payments(payment_time, sender_account, recipient_account," +
                    "payment_amount, status) VALUES" +
                    "(?, ?, ?, ?, ?)";

    private static final String PAYMENTS_BY_USER_ID =
            "SELECT p.* FROM payments p WHERE sender_account = ? " +
                    "JOIN cards c ON p.sender_account = c.account_number" +
                    "WHERE c.user_id = ? ORDER BY ? ?";

    private static final String PAYMENT_BY_ID =
            "SELECT * FROM payments WHERE payment_id = ?";



    @Override
    public List<Payment> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Payment> findById(Long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(PAYMENT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getPaymentFromResultSet(resultSet));
            }
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Payment entity) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Payment entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_PAYMENT)) {
            statement.setTimestamp(1, Timestamp.valueOf(entity.getPaymentTime()));
            statement.setLong(2, entity.getSenderAccount());
            statement.setLong(3, entity.getRecipientAccount());
            statement.setBigDecimal(4, entity.getPaymentAmount());
            statement.setString(5, entity.getPaymentStatus());

            return statement.execute();
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage());
        }
    }

    @Override
    public Payment update(Payment entity) throws DaoException {
        return null;
    }

    private Payment getPaymentFromResultSet(ResultSet resultSet) throws SQLException {
        return Payment.builder()
                .paymentId(resultSet.getLong("payment_id"))
                .paymentTime(resultSet.getTimestamp("payment_time").toLocalDateTime())
                .senderAccount(resultSet.getLong("sender_account"))
                .recipientAccount(resultSet.getLong("recipient_account"))
                .paymentAmount(resultSet.getBigDecimal("payment_amount"))
                .paymentStatus(resultSet.getString("status"))
                .build();
    }

    public List<Payment> findAllBySenderAccount(String sortBy, String sortDirection, long userId) throws DaoException {
        List<Payment> payments = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(PAYMENTS_BY_USER_ID)) {
            statement.setLong(1, userId);
            statement.setString(2, sortBy);
            statement.setString(3, sortDirection);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Payment payment = getPaymentFromResultSet(resultSet);
                payments.add(payment);
            }
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage());
        }

        return payments;
    }
}
