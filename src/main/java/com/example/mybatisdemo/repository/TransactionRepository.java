package com.example.mybatisdemo.repository;

import com.example.mybatisdemo.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction,Integer> {
}
