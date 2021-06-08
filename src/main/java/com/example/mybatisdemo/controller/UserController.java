package com.example.mybatisdemo.controller;


import com.example.mybatisdemo.model.User;
import com.example.mybatisdemo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired

    private UsersRepository usersRepository;

    @RequestMapping(value = "/add")
    public @ResponseBody Integer addNewTransaction(User user){
        User result = usersRepository.save(user);
        return result.getId();
    }

    @RequestMapping(value = "/find")
    public @ResponseBody User findByName(@RequestParam String companyName){
        User result = usersRepository.findByCompanyName(companyName);
        return result;
    }

}
