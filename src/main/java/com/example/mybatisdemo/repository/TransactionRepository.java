package com.example.mybatisdemo.repository;

import com.example.mybatisdemo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findByPayer(@Param("payer") int payer);
    List<Transaction> findByReceiver(int receiver);
    List<Transaction> findByPayerAndReceiver(int pater, int receiver);
    List<Transaction> findByStatus(int status);
    List<Transaction> findByTypeAndStatus(int type, int status);
}
