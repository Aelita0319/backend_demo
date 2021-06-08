package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.blockchain.client;
import com.example.mybatisdemo.model.User;
import com.example.mybatisdemo.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UsersRepository usersRepository;
    static client client = new client();
    static {
        try {
            client.initialize();
            client.deployTest1AndRecordAddr();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    Map<Integer,client> clients=new HashMap<>();
    @PostMapping(path="/add") // Map ONLY POST Requests

    public @ResponseBody String addNewUser (
            @RequestParam Integer id,
            @RequestParam String name
            , @RequestParam Double fixedAssets) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setId(id);
        n.setCompanyName(name);
        n.setFixedAssets(fixedAssets);
        usersRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return usersRepository.findAll();
    }
    @GetMapping(path="/test")
    public @ResponseBody String test() throws Exception {
        //test
        client.signIn(BigInteger.valueOf(1), "test",BigInteger.valueOf(10),"test");
        client.logIn(BigInteger.valueOf(1),"test");
        return "test!"+client.query(BigInteger.valueOf(1)).toString();
    }
    @GetMapping(path="/signIn")
    public @ResponseBody boolean signIn() throws Exception {

        if(client.signIn(BigInteger.valueOf(1), "test",BigInteger.valueOf(10),"test")){
            return true;
        }
        else return false;
    }
}
