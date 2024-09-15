package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{

	Users findByUsername(String username);

	//user table query
	// create table user(id int primary key, username varchar(50), password varchar(50));
	//select * from user;
	
	//insert into user(id ,username, password) values(1, 'vivek', 'vivek');
	//insert into user(id ,username, password) values(2, 'admin', 'admin');
	//insert into user(id ,username, password) values(3, 'user', 'user');
	//insert into user(id ,username, password) values(4, 'manager', 'manager');
	//insert into user(id ,username, password) values(5, 'employee', 'employee');
	
	
}
