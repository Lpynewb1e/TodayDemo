package com.example.demo.Service;

import com.example.demo.Bean.Activity;
import com.example.demo.Bean.User;
import com.example.demo.Dao.ActivityDao;
import com.example.demo.Dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ActivityController {
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private UserDao userDao;

    public ActivityController(){
        this.activityDao = activityDao;
        this.userDao = userDao;
    }

    /**
     * 添加一个活动
     * @param activity 活动
     * @return
     */
    @RequestMapping(value = "/activity/add", method = RequestMethod.POST, consumes = "application/json")
    public boolean AddActivity(@RequestBody Activity activity){
        activityDao.save(activity);
        return true;
    }


    /*
     * 查询全部活动
     */
    @RequestMapping(value = "/activity/all")
    public Object AllActivity(){
        List<Activity> list = activityDao.findAll();;
        return list;
    }

    /*
     *  查询未结束的活动
     * @param time 当前时间
     * @return list
     */

    @RequestMapping(value = "/activity/start")
    public Object start_Activity(String time){
        List<Activity> list = activityDao.findByDeadlineAfter(time);
        return list;
    }

    /*
     * 查询已经结束的活动
     * @param time 当前时间
     * @return list
     */
    @RequestMapping(value = "/activity/end")
    public Object end_Activity(String time){
        List<Activity> list = activityDao.findByDeadlineBefore(time);
        return list;
    }
}
