package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.model.Transaction;
import com.example.mybatisdemo.model.User;
import com.example.mybatisdemo.repository.TransactionRepository;
import com.example.mybatisdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path="/transaction")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/add")
    public @ResponseBody Integer addTransaction(Transaction transaction){
        Transaction result = transactionRepository.save(transaction);
        return result.getId();
    }

    @RequestMapping(value = "/update/month")
    public @ResponseBody Integer updateMonth(
            @RequestParam Integer id, @RequestParam Integer month){
        Transaction target = transactionRepository.getOne(id);
        target.setMonth(month);
        transactionRepository.save(target);
        return target.getId();
    }

    @RequestMapping(value = "/update/status")
    public @ResponseBody Integer updateStatus(
            @RequestParam Integer id, @RequestParam Integer status){
        Transaction target = transactionRepository.getOne(id);
        target.setMonth(status);
        transactionRepository.save(target);
        return target.getId();
    }

    @RequestMapping(value = "/find/payer")
    public @ResponseBody List<Transaction> findByPayer(@RequestParam String payer){
        System.out.println("payer name = "+payer);
        User user = userRepository.findByCompanyName(payer);
        int payerId = user.getId();
        List<Transaction> result = transactionRepository.findByPayer(payerId);
        return result;
    }

    @RequestMapping(value = "/find/receiver")
    public @ResponseBody List<Transaction> findByReceiver(@RequestParam String receiver){
        User user = userRepository.findByCompanyName(receiver);
        int receiverId = user.getId();
        List<Transaction> result = transactionRepository.findByReceiver(receiverId);
        return result;
    }

    @RequestMapping(value = "/find/payerandreceiver")
    public @ResponseBody List<Transaction> findByPayerAndReceiver(
            @RequestParam String payer, @RequestParam String receiver){
        User userPayer = userRepository.findByCompanyName(payer);
        User userReceiver = userRepository.findByCompanyName(receiver);
        int payerId = userPayer.getId();
        int receiverId = userReceiver.getId();
        List<Transaction> result = transactionRepository.findByPayerAndReceiver(payerId,receiverId);
        return result;
    }

    @RequestMapping(value = "/find/status")
    public @ResponseBody List<Transaction> findByStatus(@RequestParam int status){
        List<Transaction> result = transactionRepository.findByStatus(status);
        return result;
    }

    @RequestMapping(value = "/find/type")
    public @ResponseBody List<Transaction> findByType(@RequestParam int type){
        List<Transaction> result = transactionRepository.findByType(type);
        return result;
    }

    @RequestMapping(value = "/find/typeandstatus")
    public @ResponseBody List<Transaction> findByTypeAndStatus(@RequestParam int type, @RequestParam int status){
        List<Transaction> result = transactionRepository.findByTypeAndStatus(type,status);
        return result;
    }
}
