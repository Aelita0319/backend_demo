package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.model.Bank;
import com.example.mybatisdemo.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/bank")
public class BankController {
    @Autowired

    private BankRepository bankRepository;

    @RequestMapping(value = "/add")
    public @ResponseBody Integer addBank(Bank bank){
        Bank result = bankRepository.save(bank);
        return result.getId();
    }

    @RequestMapping(value = "/find")
    public @ResponseBody Bank getBankByName(@RequestParam String name){
        Bank result = bankRepository.findByName(name);
        return result;
    }

    @RequestMapping(value = "/update/args")
    public @ResponseBody Integer updateArg(
            @RequestParam String name, @RequestParam Double arg1, @RequestParam Double arg2, @RequestParam Double arg3){
        Bank target = bankRepository.findByName(name);
        target.setArg1(arg1);
        target.setArg2(arg2);
        target.setArg3(arg3);
        bankRepository.save(target);
        return target.getId();
    }

    @RequestMapping(value = "/update/interest")
    public @ResponseBody Integer updateInterest(
            @RequestParam String name, @RequestParam Double interest){
        Bank target = bankRepository.findByName(name);
        target.setInterest(interest);
        bankRepository.save(target);
        return target.getId();
    }
}
