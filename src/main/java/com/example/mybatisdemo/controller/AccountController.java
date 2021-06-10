package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.model.Account;
import com.example.mybatisdemo.model.Bank;
import com.example.mybatisdemo.model.User;
import com.example.mybatisdemo.repository.AccountRepository;
import com.example.mybatisdemo.repository.BankRepository;
import com.example.mybatisdemo.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

    @RequestMapping(value = "/add")
    public @ResponseBody Integer addAccount(Account account){
        //0:user, 1:admin, 2:bank
        Account result = accountRepository.save(account);
        if (result.getPrivilege()==0){
            User user = new User();
            user.setCompanyName(account.getName());
            userRepository.save(user);
        }
        else if (result.getPrivilege()==2){
            Bank bank = new Bank();
            bank.setName(account.getName());
            bank.setInterest(0.01);
            bank.setArg1((double) 0);
            bank.setArg2((double) 0);
            bank.setArg3((double) 0);
            bankRepository.save(bank);
        }
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
