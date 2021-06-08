package com.example.mybatisdemo.repository;

import com.example.mybatisdemo.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account,Integer> {

}
