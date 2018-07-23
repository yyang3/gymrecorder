package com.fitrecorder.gymrecorder.controller;


import com.fitrecorder.gymrecorder.beans.User;
import com.fitrecorder.gymrecorder.mysql.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="", method=RequestMethod.GET)
    public List<User> getUserList (){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String createUser(@ModelAttribute User user){
        userService.create(user);
        return "success";
    }
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser (@ModelAttribute(value = "userName") String userName) {
        return userService.getUserByUserName(userName);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String putUser (@ModelAttribute User user) {
        userService.updateUser(user);
        return "success";
    }
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public String deleteUser (@ModelAttribute (value = "userName") String userName) {
        userService.delete(userName);
        return "success";
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int countUser () {
        return userService.countUsers();
    }



}
