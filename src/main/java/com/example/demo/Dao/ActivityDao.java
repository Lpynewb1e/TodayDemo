package com.example.demo.Dao;

import com.example.demo.Bean.Activity;
import com.example.demo.Bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityDao extends JpaRepository<Activity,Long> {

    //根据id查询
    Boolean findById(String id);

    //根据当前时间来查询未结束的活动
    List<Activity> findByDeadlineAfter(String time);

    //根据当前时间来查询已结束的活动
    List<Activity> findByDeadlineBefore(String time);
}
