package com.example.demo.Service;

import com.example.demo.Bean.User;
import com.example.demo.Dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.util.List;

@RestController
public class UserController {
    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 增加一个新的用户信息到数据库：
     *
     * @param user          前端传过来的参数
     * @param bindingResult 可以理解为异常捕获类
     * @return
     */
    @RequestMapping("/students/add")
    public Object addStudentForm(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return "不符合条件，插入失败，请检查是不是有正确的请求参数";
        }
        int hash = user.getP().hashCode();
        user.setPsw(String.valueOf(hash));
        userDao.save(user);
        return user;
    }

    /**
     * 查询所有用户的信息
     */
    @GetMapping("/user/find")
    public List<User> QueryAll() {
        return userDao.findAll();
    }

    /**
     * 根据id来查询信息
     *
     * @param id 用户的id
     * @return 返回查询的结果
     */
    @RequestMapping(value = "/user/search")
    public User GetStudentById(@NotNull String userid) {
        return userDao.findById(userid);
    }

    /**
     * 进行注册
     *
     * @param user 用户这个类
     * @return 返回注册成功
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public Object RegisterUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return "Error";
        }
        List<User> list = userDao.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (user.getName().equals(list.get(i).getName()))
                return "false";
        }
        String psw = user.getP();
        user.setPsw(String.valueOf(psw.hashCode()));
        userDao.save(user);
        return user;
    }

    /**
     * 登录
     * @param id 用户名
     * @param psw 用户的密码
     * @return 返回用户
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Object Login(@Valid String id, @Valid String psw) {
        User user = new User();
        user = userDao.findById(id);
        if (String.valueOf(psw.hashCode()).equals(user.getPsw())) {
            String str = user.getName() + user.getPsw();
            String token = String.valueOf(str.hashCode());
            user.setToken(token);
            userDao.save(user);
            return user;
        }
        return "false";
    }

    /**
     * 修改密码
     * @param psw 密码
     * @param token 身份验证
     * @return 返回用户
     */
    @RequestMapping("/user/Change_psw")
    public Object Change_Psw(@Valid String psw,@Valid String token){
        User user = userDao.findByToken(token);
        if(user != null) {
            int password = psw.hashCode();
            user.setPsw(String.valueOf(password));
            userDao.save(user);
        }
        else
            return "false";
        return user;
    }

    /**
     * 修改昵称
     * @param name 前端传来的名字
     * @param token 用户的验证
     * @return 返回用户
     */
    @RequestMapping("/user/change_name")
    public Object Change_Name(@Valid String name,@Valid String token){
        User u = userDao.findByToken(token);
        if(u != null){
            u.setName(name);
            userDao.save(u);
        }else
            return "false";
        return u;
    }

    /**
     * 退出账号
     * @param token 身份验证
     * @return 无
     */
    @RequestMapping("/user/login_out")
    public String Login_Out(@Valid String token){
        User u = userDao.findByToken(token);
        if(u != null){
            u.setToken("");
            userDao.save(u);
        }else
            return "false";
        return "true";
    }

}
