package com.kpi.payments.model.dao;

import com.kpi.payments.model.dao.impl.AccountDao;
import com.kpi.payments.model.dao.impl.DaoFactoryImpl;
import com.kpi.payments.model.dao.impl.PaymentDao;
import com.kpi.payments.model.dao.impl.UserDao;

import java.util.Objects;

public abstract class DaoFactory {

    private static volatile DaoFactory daoFactory;

    public abstract PaymentDao createPaymentDao();

    public abstract AccountDao createAccountDao();

    public abstract UserDao createUserDao();

    protected DaoFactory() {

    }

    public static DaoFactory getInstance() {
        if (Objects.isNull(daoFactory)) {
            synchronized (DaoFactory.class) {
                if (Objects.isNull(daoFactory)) {
                    daoFactory = new DaoFactoryImpl();
                }
            }
        }

        return daoFactory;
    }
}
