package com.kpi.payments.service;

import com.kpi.payments.controller.LoginServlet;
import com.kpi.payments.entity.User;
import com.kpi.payments.exception.DaoException;
import com.kpi.payments.exception.NotFoundException;
import com.kpi.payments.model.dao.ConnectionPool;
import com.kpi.payments.model.dao.DaoFactory;
import com.kpi.payments.model.dao.impl.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {

    private final UserDao userDao;

    private final static Logger logger = LogManager.getLogger(LoginServlet.class);

    public UserService() {
        userDao = DaoFactory.getInstance().createUserDao();
        userDao.setConnection(ConnectionPool.getConnection());
    }

    public User login(String email, String password) {
        try {
            return userDao.findByEmailAndPassword(email, password).orElseThrow(() -> new NotFoundException("User login failed"));
        } catch (DaoException exception) {
            logger.error(exception.getMessage());
        }
        return null;
    }

    public void lockUser(long userId) {
        try {
            userDao.lockUser(userId);
        } catch (DaoException exception) {
            logger.error(exception.getMessage());
        }

    }

    public void unlockUser(long userId) {
        try {
            userDao.unlockUser(userId);
        } catch (DaoException exception) {
            logger.error(exception.getMessage());
        }

    }
}
