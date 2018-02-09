package com.jaky.server.dao;

import com.jaky.server.bean.User;

public interface UserDao {

    boolean insert(User user);

    User query(String username);

    User query(String username, String password);

}
