package com.kpi.payments.service;

import com.kpi.payments.model.dao.impl.AccountDao;

public class ServiceFactoryImpl extends ServiceFactory {
    @Override
    public PaymentService createPaymentService() {
        return null;
    }

    @Override
    public AccountService createAccountService() {
        return null;
    }

    @Override
    public UserService createUserService() {
        return null;
    }
}
