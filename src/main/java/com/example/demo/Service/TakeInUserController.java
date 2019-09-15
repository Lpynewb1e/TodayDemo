package com.example.demo.Service;

import com.example.demo.Bean.Activity;
import com.example.demo.Bean.TakeInUser;
import com.example.demo.Bean.User;
import com.example.demo.Dao.ActivityDao;
import com.example.demo.Dao.TakeInUserDao;
import com.example.demo.Dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class TakeInUserController {
    @Autowired
    private TakeInUserDao take_in_user_dao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ActivityDao activityDao;

    public TakeInUserController(){
        this.take_in_user_dao = take_in_user_dao;
        this.userDao = userDao;
        this.activityDao = activityDao;
    }

    /**
     * 报名参加活动
     * @param token 用户验证
     * @param id 活动id
     * @param take 这个类
     * @return  返回true or false
     */
    @RequestMapping("/takein/add")
    public Object Activity_Add(@Valid TakeInUser take, String token, Long id){
        User u = userDao.findByToken(token);
        if(u != null){
            Optional<Activity> flag = activityDao.findById(id);
            if(flag.isPresent()){
                take_in_user_dao.save(take);
            }else
                return "id false";
        }else
            return token;
        return take;
    }

    /**
     *
     */
}
