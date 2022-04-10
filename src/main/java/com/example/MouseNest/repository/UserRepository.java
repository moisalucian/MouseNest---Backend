package com.example.MouseNest.repository;

import com.example.MouseNest.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> { //CrudRepository<tipClasa, tipPrimaryKey>

    User findUserByEmail(String email);


}
