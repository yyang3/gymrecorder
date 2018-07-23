package com.fitrecorder.gymrecorder.mysql;

import com.fitrecorder.gymrecorder.beans.User;

import java.util.List;

public interface IUserService {

    void create (User user);

    void delete (String userName);

    Integer countUsers();

    List<User> getAllUsers();

    User getUserByUserName(String userName);

    void updateUser (User user);
}
