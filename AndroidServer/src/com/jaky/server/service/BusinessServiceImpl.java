package com.jaky.server.service;

import com.jaky.server.bean.User;
import com.jaky.server.dao.DaoFactory;
import com.jaky.server.dao.UserDao;
import com.jaky.server.exception.UserExistException;

public class BusinessServiceImpl implements BusinessService {

    UserDao dao = DaoFactory.getInstance().createUserDao();

    public void register(User user) throws UserExistException {
        if( (dao.query(user.getUsername())) != null ){
            //这里抛编译时异常的原因是：希望上一层程序处理这个异常，以给用户一个友好的提示。
            //抛编译时异常需要在方法上显式的申明。
            throw new UserExistException("注册的用户名已存在！");
        }
        dao.insert(user);
    }

    public User login(String username, String password){
        User user = dao.query(username, password);
        return user;
    }

}
