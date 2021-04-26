package com.example.mybatisdemo.repository;

import com.example.mybatisdemo.model.Bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@RepositoryRestResource(path = "rest")
public interface BanksRepository extends CrudRepository<Bank, Integer> {
    List<Bank> findByName(@Param("name") String name);
}
