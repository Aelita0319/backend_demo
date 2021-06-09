package com.example.mybatisdemo.controller;


import com.example.mybatisdemo.model.User;
import com.example.mybatisdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.BigInteger;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired

    private UserRepository userRepository;

    @RequestMapping(value = "/add")
    public @ResponseBody Integer addUser(User user) throws Exception {
        User result = userRepository.save(user);
        MainController.client.addBalance(BigInteger.valueOf(result.getId()),
                BigDecimal.valueOf(result.getFixedAssets()).toBigInteger());
        return result.getId();
    }

    @RequestMapping(value = "/update/fixedAssets")
    public @ResponseBody Integer updateFixedAssets(
            @RequestParam String companyName, @RequestParam Double fixedAssets){
        User target = userRepository.findByCompanyName(companyName);
        target.setFixedAssets(fixedAssets);
        userRepository.save(target);
        return target.getId();
    }

    @RequestMapping(value = "/update/currentAssets")
    public @ResponseBody Integer updateCurrentAssets(
            @RequestParam String companyName, @RequestParam Double currentAssets){
        User target = userRepository.findByCompanyName(companyName);
        target.setCurrentAssets(currentAssets);
        return target.getId();
    }

    @RequestMapping(value = "/update/loans")
    public @ResponseBody Integer updateLoans(
            @RequestParam String companyName, @RequestParam Double loans){
        User target = userRepository.findByCompanyName(companyName);
        target.setLoans(loans);
        return target.getId();
    }

    @RequestMapping(value = "/find")
    public @ResponseBody User findUserByName(@RequestParam String companyName){
        User result = userRepository.findByCompanyName(companyName);
        return result;
    }

}
