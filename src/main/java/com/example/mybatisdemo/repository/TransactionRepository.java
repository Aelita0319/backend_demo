package com.example.mybatisdemo.repository;

import com.example.mybatisdemo.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction,Integer> {
    List<Transaction> findByPayer(@Param("payer") int payerId);
}
