package com.jaky.server.dao;

import com.jaky.server.bean.User;
import com.jaky.server.exception.DaoException;
import com.jaky.server.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJdbcImpl implements UserDao {

    @Override
    public boolean insert(User user) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean res = false;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "insert into users(id,username,password,email,birthday) values(?,?,?,?,?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setDate(5, new java.sql.Date(user.getBirthday().getTime()));
            statement.executeUpdate();
            res = true;
        } catch (SQLException e) {
            res = false;
            System.out.println("添加用户失败！");
        } finally {
            JdbcUtils.release(conn, statement, resultSet);
            return res;
        }
    }


    @Override
    public User query(String username) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select id,username,password,email,birthday from users where username=?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setBirthday(resultSet.getDate("birthday"));
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            JdbcUtils.release(conn, statement, resultSet);
        }
    }


    @Override
    public User query(String username, String password) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select id,username,password,email,birthday from users where username=? and password=?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setBirthday(resultSet.getDate("birthday"));
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            JdbcUtils.release(conn, statement, resultSet);
        }
    }

    public boolean update(String username, String password){
        return false;
    }

}
