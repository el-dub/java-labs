package com.kpi.payments.service;

import com.kpi.payments.controller.LoginServlet;
import com.kpi.payments.entity.Payment;
import com.kpi.payments.exception.DaoException;
import com.kpi.payments.model.dao.ConnectionPool;
import com.kpi.payments.model.dao.DaoFactory;
import com.kpi.payments.model.dao.impl.PaymentDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PaymentService {

    private final PaymentDao paymentDao;

    private final static Logger logger = LogManager.getLogger(LoginServlet.class);

    public PaymentService() {
        paymentDao = DaoFactory.getInstance().createPaymentDao();
        paymentDao.setConnection(ConnectionPool.getConnection());
    }

    public List<Payment> findByUserId(String sortBy, String sortDirection, Long userId) {
        try {
            return paymentDao.findAllBySenderAccount(sortBy, sortDirection, userId);
        } catch (DaoException exception) {
            logger.error(exception.getMessage());
        }
        return null;
    }

    public void createPayment(Payment payment) {
        try {
            paymentDao.create(payment);
        } catch (DaoException exception) {
            logger.error(exception.getMessage());
        }

    }
}
