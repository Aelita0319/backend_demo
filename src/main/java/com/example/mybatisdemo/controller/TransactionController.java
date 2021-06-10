package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.model.Transaction;
import com.example.mybatisdemo.model.User;
import com.example.mybatisdemo.repository.BankRepository;
import com.example.mybatisdemo.repository.TransactionRepository;
import com.example.mybatisdemo.repository.UserRepository;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping(path="/transaction")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

//    @RequestMapping(value = "/add")
//    public @ResponseBody Integer addTransaction(Transaction transaction){
//        Transaction result = transactionRepository.save(transaction);
//        return result.getId();
//    }

    @RequestMapping(value = "/add")
    public @ResponseBody Integer addTransaction(
            @RequestParam String payer, @RequestParam String receiver, @RequestParam Integer month,
            @RequestParam Double amount, @RequestParam Integer status, @RequestParam Integer type) throws Exception {
        Transaction transaction = new Transaction();
        Integer payerId;
        if (type==1) payerId = bankRepository.findByName(payer).getId();
        else payerId = userRepository.findByCompanyName(payer).getId();
        Integer receiverId = userRepository.findByCompanyName(receiver).getId();
        transaction.setPayer(payerId);
        transaction.setReceiver(receiverId);
        transaction.setMonth(month);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setStatus(status);
        transactionRepository.save(transaction);

        //type: 0:buy, 1:loan, 2:receipt, 3.own
        if (type==0) { // buy: user to user
            MainController.client.buy(BigInteger.valueOf(payerId),
                    BigInteger.valueOf(receiverId), BigDecimal.valueOf(amount).toBigInteger());
        }
        else if (type==1){
            MainController.client.loans(BigInteger.valueOf(receiverId),
                    BigInteger.valueOf(payerId), BigDecimal.valueOf(amount).toBigInteger());
        }
        else if (type==2){
            MainController.client.receipt(BigInteger.valueOf(payerId),
                    BigInteger.valueOf(receiverId), BigDecimal.valueOf(amount).toBigInteger());
        }
        else if (type==3){
            MainController.client.addBalance(BigInteger.valueOf(payerId),
                    BigDecimal.valueOf(amount).toBigInteger());
        }

        return transaction.getId();
    }

    @RequestMapping(value = "/update/month")
    public @ResponseBody Integer updateMonth(
            @RequestParam Integer id, @RequestParam Integer month){
        Transaction target = transactionRepository.getOne(id);
        target.setMonth(month);
        transactionRepository.save(target);
        return target.getId();
    }

    //'0:unfinished, 1:failed, 2:finished'
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
        User user = userRepository.findByCompanyName(payer);
        try {
            int payerId = user.getId();
            List<Transaction> result = transactionRepository.findByPayer(payerId);
            return result;
        }
        catch (NullPointerException e){
            return null;
        }
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
    public @ResponseBody List<Transaction> findByStatus(@RequestParam Integer status){
        List<Transaction> result = transactionRepository.findByStatus(status);
        return result;
    }

    @RequestMapping(value = "/find/type")
    public @ResponseBody List<Transaction> findByType(@RequestParam Integer type){
        List<Transaction> result = transactionRepository.findByType(type);
        return result;
    }

    @RequestMapping(value = "/find/typeandstatus")
    public @ResponseBody List<Transaction> findByTypeAndStatus(@RequestParam Integer type, @RequestParam Integer status){
        List<Transaction> result = transactionRepository.findByTypeAndStatus(type,status);
        return result;
    }

    @RequestMapping(value = "/check")
    public @ResponseBody Boolean checkBlockchain() throws Exception {
        int total = MainController.client.getNumberOfDeals().intValue();
        for (int i=0;i<total;i++){
            Tuple4<String, BigInteger, BigInteger, BigInteger> deal =
                    MainController.client.getDeal(BigInteger.valueOf(i));
            Transaction cur = transactionRepository.getOne(i+1);
            int type = cur.getType();
            //type: 0:buy, 1:loan, 2:receipt, 3.own
            if (type==0) { // buy: user to user
                if (!deal.getValue1().equals("buy")|| !deal.getValue2().equals(BigInteger.valueOf(cur.getPayer())) ||
                        !deal.getValue3().equals(BigInteger.valueOf(cur.getReceiver()))||
                                !deal.getValue4().equals(BigDecimal.valueOf(cur.getAmount()).toBigInteger())){
                    return false;
                }
            }
            else if (type==1){
                if (!deal.getValue1().equals("loan")|| !deal.getValue2().equals(BigInteger.valueOf(cur.getReceiver())) ||
                        !deal.getValue3().equals(BigInteger.valueOf(cur.getPayer()))||
                        !deal.getValue4().equals(BigDecimal.valueOf(cur.getAmount()).toBigInteger())){
                    return false;
                }
            }
            else if (type==2){
                if (!deal.getValue1().equals("receipt")|| !deal.getValue2().equals(BigInteger.valueOf(cur.getPayer())) ||
                        !deal.getValue3().equals(BigInteger.valueOf(cur.getReceiver()))||
                        !deal.getValue4().equals(BigDecimal.valueOf(cur.getAmount()).toBigInteger())){
                    return false;
                }
            }
            else if (type==3){
                if (!deal.getValue1().equals("addBalance")|| !deal.getValue2().equals(BigInteger.valueOf(cur.getPayer())) ||
                        !deal.getValue3().equals(BigInteger.valueOf(0)) ||
                        !deal.getValue4().equals(BigDecimal.valueOf(cur.getAmount()).toBigInteger())){
                    return false;
                }
            }
        }
        return true;
    }

    @RequestMapping(value = "/find/all")
    public @ResponseBody List<Transaction> findByAll(){
        List<Transaction> list = transactionRepository.findAll();
        return list;
    }
}
