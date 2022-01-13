package com.kpi.payments.model.dao.impl;

import com.kpi.payments.entity.RoleType;
import com.kpi.payments.entity.User;
import com.kpi.payments.exception.DaoException;
import com.kpi.payments.model.dao.AbstractDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDAO<Long, User> {

    private static final String USER_BY_EMAIL_PASSWORD =
            "SELECT * FROM users WHERE email = ? AND password = ?";

    public static final String UPDATE_LOCKED = "UPDATE гіукі SET locked = ? WHERE user_id = ?";

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(USER_BY_EMAIL_PASSWORD)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getUserFromResultSet(resultSet));
            }
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage());
        }
        return Optional.empty();
    }

    public void lockUser(long userId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_LOCKED)) {
            statement.setBoolean(1, true);
            statement.setLong(2, userId);

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage());
        }
    }

    public void unlockUser(long userId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_LOCKED)) {
            statement.setBoolean(1, false);
            statement.setLong(2, userId);

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage());
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .userId(resultSet.getLong("user_id"))
                .lastName(resultSet.getString("last_name"))
                .firstName(resultSet.getString("first_name"))
                .middleName(resultSet.getString("middle_name"))
                .email(resultSet.getString("email"))
                .phone(resultSet.getString("phone"))
                .dateOfBirth(resultSet.getTimestamp("payment_time").toLocalDateTime().toLocalDate())
                .locked(resultSet.getBoolean("locked"))
                .role(RoleType.valueOf(resultSet.getString("role")))
                .build();
    }
}
