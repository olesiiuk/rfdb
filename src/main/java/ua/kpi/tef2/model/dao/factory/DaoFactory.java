package ua.kpi.tef2.model.dao.factory;

import ua.kpi.tef2.model.dao.UserDaoImpl;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract UserDaoImpl createUserDao();

    public static DaoFactory getInstance() {

        DaoFactory localInstance = daoFactory;
        if (localInstance == null) {
            synchronized (DaoFactory.class) {
                localInstance = daoFactory;
                if (localInstance == null) {
                    daoFactory = localInstance = new JdbcDaoFactory();
                }
            }
        }

        return localInstance;
    }


}
