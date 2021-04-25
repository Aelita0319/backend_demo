package com.example.mybatisdemo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.mybatisdemo.model.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UsersRepository extends CrudRepository<User, Integer> {

}