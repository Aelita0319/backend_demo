package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.model.Transaction;
import com.example.mybatisdemo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/transaction")
public class TransactionController {
    @Autowired

    private TransactionRepository transactionRepository;

    @RequestMapping(value = "/add")
    public @ResponseBody Integer addNewTransaction(Transaction transaction){
        Transaction result = transactionRepository.save(transaction);
        return result.getId();
    }
}
