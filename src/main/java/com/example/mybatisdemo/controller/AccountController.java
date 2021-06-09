package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.model.Account;
import com.example.mybatisdemo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/account")
public class AccountController {
    @Autowired

    private AccountRepository accountRepository;

    @RequestMapping(value = "/add")
    public @ResponseBody Integer addAccount(Account account){
        Account result = accountRepository.save(account);
        return result.getId();
    }

    @RequestMapping(value = "/check")
    public @ResponseBody Boolean checkAccount(@RequestParam String name, @RequestParam String password){
        Account target = accountRepository.findByName(name);
        return target.getPassword().equals(password);
    }
}
