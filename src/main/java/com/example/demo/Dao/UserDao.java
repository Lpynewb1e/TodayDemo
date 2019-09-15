package com.example.demo.Dao;

import com.example.demo.Bean.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    //通过名字找到用户
    User findByName(String name);
    //通过token找到用户
    User findByToken(String token);

    User findById(String id);
}
