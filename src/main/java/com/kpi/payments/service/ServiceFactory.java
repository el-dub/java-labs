package com.kpi.payments.service;

import com.kpi.payments.model.dao.DaoFactory;

import java.util.Objects;

public abstract class ServiceFactory {
    private static volatile ServiceFactory serviceFactory;

    public abstract PaymentService createPaymentService();

    public abstract AccountService createAccountService();

    public abstract UserService createUserService();

    protected ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
        if (Objects.isNull(serviceFactory)) {
            synchronized (DaoFactory.class) {
                if (Objects.isNull(serviceFactory)) {
                    serviceFactory = new ServiceFactoryImpl();
                }
            }
        }

        return serviceFactory;
    }
}
