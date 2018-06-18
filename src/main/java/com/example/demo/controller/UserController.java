package com.example.demo.controller;

import com.example.demo.JsonEntity;
import com.example.demo.ResponseHelper;
import com.example.demo.dao.entity.User;
import com.example.demo.dao.repository.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String addUser(@RequestBody User user) {
        userRepository.save(user);
        return "OK";
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable("userId") Integer userId, @RequestBody User user) {
        user.setId(userId);
        userRepository.save(user);
        return "OK";
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("userId") Integer userId) {
        userRepository.delete(userId);
        return "OK";
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User queryByUserId(@PathVariable("userId") Integer userId) {
        return userRepository.findOne(userId);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAll() throws InterruptedException {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/company/{companyId}/users", method = RequestMethod.GET)
    public List<User> queryByCompanyId(@PathVariable(value = "companyId") Integer companyId) {
        return userRepository.findByCompanyId(companyId);
    }

//    @RequestMapping(value = "/user/userName/{userName}", method = RequestMethod.GET)
//    public JsonEntity<User> queryByUserName(@PathVariable("userName") String userName) {
//        return ResponseHelper.createInstance(userRepository.findOne(userId));
//    }
}
