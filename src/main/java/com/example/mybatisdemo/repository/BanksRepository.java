package com.example.mybatisdemo.repository;

import com.example.mybatisdemo.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@RepositoryRestResource(path = "rest")
public interface BanksRepository extends JpaRepository<Bank, Integer> {
    Bank findByName(@Param("name") String name);
}
