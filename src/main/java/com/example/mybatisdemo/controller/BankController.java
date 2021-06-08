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

//    @RequestMapping(value = "/update")
//    public @ResponseBody String updateArg(){}
}
