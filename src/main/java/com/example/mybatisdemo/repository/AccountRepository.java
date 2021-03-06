package com.example.mybatisdemo.repository;

import com.example.mybatisdemo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findByName(String name);

}
