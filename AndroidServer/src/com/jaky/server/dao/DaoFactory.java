package com.jaky.server.dao;

import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {

    private UserDao dao = null;

    private DaoFactory(){
        try{
            InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
            Properties prop = new Properties();
            prop.load(in);
            String daoClassName = prop.getProperty("userdao");
            dao = (UserDao) Class.forName(daoClassName).newInstance();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private static final DaoFactory factory = new DaoFactory();

    public static DaoFactory getInstance(){
        return factory;
    }

    public UserDao createUserDao(){
        return dao;
    }
}
