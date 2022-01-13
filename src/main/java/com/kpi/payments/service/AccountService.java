package com.kpi.payments.service;

import com.kpi.payments.controller.LoginServlet;
import com.kpi.payments.entity.Account;
import com.kpi.payments.exception.DaoException;
import com.kpi.payments.model.dao.ConnectionPool;
import com.kpi.payments.model.dao.DaoFactory;
import com.kpi.payments.model.dao.impl.AccountDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AccountService {

    private final static Logger logger = LogManager.getLogger(LoginServlet.class);

    private final AccountDao accountDao;

    public AccountService() {
        accountDao = DaoFactory.getInstance().createAccountDao();
        accountDao.setConnection(ConnectionPool.getConnection());
    }

    public List<Account> findAccountsByUserId(String sortBy, String sortDirection, long userId) {
        try {
            return accountDao.findAllByUserId(sortBy, sortDirection, userId);
        } catch (DaoException exception) {
            logger.error(exception.getMessage());
        }
        return null;
    }

    public void lockAccount(long accountId) {
        try {
            accountDao.lockAccount(accountId);
        } catch (DaoException exception) {
            logger.error(exception.getMessage());
        }
    }

    public void unlockAccount(long accountId) {
        try {
            accountDao.unlockAccount(accountId);
        } catch (DaoException exception) {
            logger.error(exception.getMessage());
        }
    }
}
