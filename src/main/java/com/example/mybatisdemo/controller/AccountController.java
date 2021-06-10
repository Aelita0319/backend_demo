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
    public @ResponseBody Integer checkAccount(@RequestParam String name, @RequestParam String password){
        try {
            Account target = accountRepository.findByName(name);
            if (target.getPassword().equals(password)){
                return target.getPrivilege();
            }
            else return -1;
        }
        catch (NullPointerException e){
            return -1;
        }
    }
}
