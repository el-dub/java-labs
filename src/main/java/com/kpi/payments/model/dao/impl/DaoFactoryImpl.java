package com.kpi.payments.model.dao.impl;

import com.kpi.payments.model.dao.DaoFactory;

public class DaoFactoryImpl extends DaoFactory {

    public DaoFactoryImpl() {
        super();
    }
    @Override
    public PaymentDao createPaymentDao() {
        return new PaymentDao();
    }

    @Override
    public AccountDao createAccountDao() {
        return new AccountDao();
    }

    @Override
    public UserDao createUserDao() {
        return new UserDao();
    }
}
