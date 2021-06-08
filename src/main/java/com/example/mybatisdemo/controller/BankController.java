package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.model.Bank;
import com.example.mybatisdemo.model.Transaction;
import com.example.mybatisdemo.repository.BanksRepository;
import com.example.mybatisdemo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/bank")
public class BankController {
    @Autowired

    private BanksRepository banksRepository;

    @RequestMapping(value = "/add")
    public @ResponseBody Integer addBank(Bank bank){
        Bank result = banksRepository.save(bank);
        return result.getId();
    }

    @RequestMapping(value = "/find")
    public @ResponseBody Bank getBankByName(@RequestParam String name){
        Bank result = banksRepository.findByName(name);
        return result;
    }

    @RequestMapping(value = "/update/args")
    public @ResponseBody Integer updateArg(
            @RequestParam String name, @RequestParam Double arg1, @RequestParam Double arg2, @RequestParam Double arg3){
        Bank target = banksRepository.findByName(name);
        target.setArg1(arg1);
        target.setArg2(arg2);
        target.setArg3(arg3);
        banksRepository.save(target);
        return target.getId();
    }

    @RequestMapping(value = "/update/interest")
    public @ResponseBody Integer updateInterest(
            @RequestParam String name, @RequestParam Double interest){
        Bank target = banksRepository.findByName(name);
        target.setInterest(interest);
        banksRepository.save(target);
        return target.getId();
    }
}
