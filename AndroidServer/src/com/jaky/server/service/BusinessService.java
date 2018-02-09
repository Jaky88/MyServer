package com.jaky.server.service;

import com.jaky.server.bean.User;
import com.jaky.server.exception.UserExistException;

public interface BusinessService {

    void register(User user) throws UserExistException;

    User login(String username, String password);

}
